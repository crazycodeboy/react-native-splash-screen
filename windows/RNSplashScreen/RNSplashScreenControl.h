#pragma once

#include "winrt/Windows.UI.Xaml.h"
#include "winrt/Windows.UI.Xaml.Markup.h"
#include "winrt/Windows.UI.Xaml.Interop.h"
#include "winrt/Windows.UI.Xaml.Controls.Primitives.h"
#include "RNSplashScreenControl.g.h"

namespace winrt::RNSplashScreen::implementation
{
    struct RNSplashScreenControl : RNSplashScreenControlT<RNSplashScreenControl>
    {
        RNSplashScreenControl();

        Windows::Foundation::IAsyncAction LoadSplashScreenInformation();

        void UpdateSplashScreenVisuals(std::string backgroundColor, Windows::Foundation::Uri splashImageUri);

        static std::optional<Windows::UI::Color> ParseColor(std::string color);

    };
}

namespace winrt::RNSplashScreen::factory_implementation
{
    struct RNSplashScreenControl : RNSplashScreenControlT<RNSplashScreenControl, implementation::RNSplashScreenControl>
    {
    };
}
