package com.chihuasdevs.androidui.appScreens.appView
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chihuasdevs.androidui.overlays.loadings.FullScreenLoadingView
import com.chihuasdevs.androidui.overlays.loadings.FullScreenLoadingViewModel
import com.chihuasdevs.kmmCoreKit.base.BusinessBaseFlow
import com.chihuasdevs.kmmCoreKit.enum.UIAnimation
import com.chihuasdevs.kmmCoreKit.interfaces.businessToUI.screens.BusinessAppViewDelegate
import com.chihuasdevs.kmmCoreKit.interfaces.uiToBusiness.AppUIEventsDelegate
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class AppViewModel() : ViewModel(), BusinessAppViewDelegate {


    data class AnyViewWrapper(val view: @Composable () -> Unit, val id: Int)


    // State para representar la vista actual q
    private var currentViewValue: (@Composable () -> Unit)? = null
    private val _currentView = mutableStateOf(currentViewValue)
    var currentView: (@Composable () -> Unit)? by _currentView

    var oldView: (@Composable () -> Unit)? by mutableStateOf(null)

    private val _isCurrentViewVisible = mutableStateOf(true) // Nuevo estado para controlar la visibilidad
    var isCurrentViewVisible: State<Boolean> = _isCurrentViewVisible // Exponer el estado como State

    private val _isOldViewVisible = mutableStateOf(true) // Nuevo estado para controlar la visibilidad
    var isOldViewVisible: State<Boolean> = _isOldViewVisible // Exponer el estado como State

    private val _currentViewXOffset = mutableStateOf(0f)
    var currentViewXOffset: State<Float> = _currentViewXOffset

    private val _oldViewXOffset = mutableStateOf(0f)
    var oldViewXOffset: State<Float> = _oldViewXOffset

    var currentViewAnimateWhenXOffsetChange = false
    var oldViewAnimateWhenXOffsetChange = false

    var screenWith:Float = 0f

    private val overlayViewsQueue = mutableStateListOf<AnyViewWrapper>()
    var currentOverlayView by mutableStateOf<AnyViewWrapper?>(null)

    constructor(appUIEventsDelegate: AppUIEventsDelegate?) : this() {

    }

    fun setCurrentView(view: @Composable () -> Unit, animation: UIAnimation?) {
        oldView = _currentView.value
        viewModelScope.launch {
            setCurrentViewFunc(null)
            if (animation != null) {
                _isOldViewVisible.value = true
            }
            else {
                _isOldViewVisible.value = false
                delay(5)
                setCurrentViewFunc(view)
                return@launch
            }

            setOldViewXOffset(0f, false)

            val xOffset = when (animation) {
                UIAnimation.FORWARD -> {
                    delay(5)
                    screenWith
                }
                UIAnimation.BACK -> {
                    delay(50)
                    screenWith * -1
                }
                else -> 0f
            }

            setCurrentViewXOffset(xOffset, false)
            setCurrentViewFunc(view)

            delay(200)
            _isCurrentViewVisible.value = true
            val currentOffset = if (animation == UIAnimation.FORWARD) screenWith / 2 else screenWith / 2 * -1
            val oldOffset = if (animation == UIAnimation.FORWARD) screenWith / 2 * -1 else screenWith / 2
            setCurrentViewXOffset(currentOffset, true)
            setOldViewXOffset(oldOffset, true)
            setCurrentViewXOffset(0f, true)
            val finalOldOffset = if (animation == UIAnimation.FORWARD) screenWith * -1 else screenWith
            setOldViewXOffset(finalOldOffset, true)
            delay(5)
            oldView = null

        }
    }

    fun closeOverlayView(id: Int?) {
        id?.let { nonNullId ->
            overlayViewsQueue.removeAll { it.id == nonNullId }
        }

        removeCurrentOverlayViewFromQueue()
        viewModelScope.launch {
            currentOverlayView = null
            if (overlayViewsQueue.isNotEmpty()) {
                currentOverlayView = overlayViewsQueue.last()
            }
        }
    }

    fun showOverlayView(view: @Composable () -> Unit): Int {
        val id = overlayViewsQueue.size + 1
        if (overlayViewsQueue.isNotEmpty()) {
            overlayViewsQueue.add(AnyViewWrapper(view = view, id = id))
            if (currentOverlayView == null) {
                currentOverlayView = overlayViewsQueue.last()
            }
        } else {
            val anyViewWrapper = AnyViewWrapper(view = view, id = id)
            overlayViewsQueue.add(anyViewWrapper)
            viewModelScope.launch {
                currentOverlayView = anyViewWrapper
            }
        }
        return id
    }

    private fun setCurrentViewFunc(view: (@Composable () -> Unit)?) {
        currentViewValue = view
        currentView = currentViewValue
    }

    private fun setCurrentViewXOffset( offset: Float, animated: Boolean){
        currentViewAnimateWhenXOffsetChange = animated
        _currentViewXOffset.value = offset
    }

    private fun setOldViewXOffset( offset: Float, animated: Boolean){
        oldViewAnimateWhenXOffsetChange = animated
        _oldViewXOffset.value = offset
    }

    private fun removeCurrentOverlayViewFromQueue() {
        currentOverlayView?.let { nonNullOverlayView ->
            overlayViewsQueue.removeAll { it.id == nonNullOverlayView.id }
        }
    }

//    fun showSplash(animation: UIAnimation?){
//
//        val splashViewModel = SplashViewModel(null)
//        val random = Random.nextInt(10)
//        splashViewModel.test.value = random
//
//        val splashView = SplashView(splashViewModel)
//
//        AndroidUI.getAppViewModel()?.setCurrentView({ splashView.SplashView()}, animation)
//
//    }

    override fun closeOverlayView(businessFlow: BusinessBaseFlow, id: Int?) {
        TODO("Not yet implemented")
    }

    override fun showLoading(businessFlow: BusinessBaseFlow, show: Boolean, id: Int?): Int {

        return if (show) {
            showOverlayView {
                FullScreenLoadingView(viewModel = FullScreenLoadingViewModel()).FullScreenLoadingView()
            }
        } else {
            closeOverlayView(id)
            id ?: -1
        }
    }

}
