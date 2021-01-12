#pragma once

#include "pch.h"
#include "NativeModules.h"
#include "RNSplashScreenControl.h"

using namespace winrt::Microsoft::ReactNative;

namespace winrt::RNSplashScreen
{
    REACT_MODULE(RNSplashScreen, L"SplashScreen");
    struct RNSplashScreen
    {
        const std::string Name = "SplashScreen";
        ReactContext reactContext = nullptr;

        winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> getSplashScreenControl();
        winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> getSplashScreenControl(xaml::DependencyObject startNode);

        REACT_INIT(Init);
        void Init(ReactContext const& context) noexcept;

        REACT_METHOD(Show, L"show");
        void Show() noexcept;

        REACT_METHOD(Hide, L"hide");
        void Hide() noexcept;
    };
}
