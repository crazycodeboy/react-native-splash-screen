#include "pch.h"
#include "RNSplashScreen.h"

winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> winrt::RNSplashScreen::RNSplashScreen::getSplashScreenControl()
{
    xaml::FrameworkElement root{ nullptr };

    auto window = xaml::Window::Current();

    if (window != nullptr)
    {
        root = window.Content().as<xaml::FrameworkElement>();
    } else if (auto xamlRoot = React::XamlUIService::GetXamlRoot(reactContext.Properties().Handle()))
    {
        root = xamlRoot.Content().as<xaml::FrameworkElement>();
    }

    if (!root)
    {
        return nullptr;
    }

    return getSplashScreenControl(root);
}

winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> winrt::RNSplashScreen::RNSplashScreen::getSplashScreenControl(xaml::DependencyObject startNode)
{
    const int count = winrt::Windows::UI::Xaml::Media::VisualTreeHelper::GetChildrenCount(startNode);
    winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> result = nullptr;
    for (int i = 0; i < count; i++)
    {
        xaml::DependencyObject current = winrt::Windows::UI::Xaml::Media::VisualTreeHelper::GetChild(startNode, i);
        auto temp{ current.try_as<winrt::RNSplashScreen::RNSplashScreenControl>() };
        if (temp)
        {
            return temp;
        } else
        {
            result = getSplashScreenControl(current);
            if (result)
            {
                break;
            }
        }
    }
    return result;
}

void winrt::RNSplashScreen::RNSplashScreen::Init(ReactContext const& context) noexcept
{
    reactContext = context;
}

void winrt::RNSplashScreen::RNSplashScreen::Show() noexcept
{
    reactContext.UIDispatcher().Post([=]()
        {
            winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> control = getSplashScreenControl();
            if (control != nullptr)
            {
                control.Opacity(1);
                control.IsHitTestVisible(true);
                control.IsEnabled(true);
            }
        }
    );
}

void winrt::RNSplashScreen::RNSplashScreen::Hide() noexcept
{
    reactContext.UIDispatcher().Post([=]()
        {
            winrt::impl::com_ref<winrt::RNSplashScreen::RNSplashScreenControl> control = getSplashScreenControl();
            if (control != nullptr)
            {
                control.Opacity(0);
                control.IsHitTestVisible(false);
                control.IsEnabled(false);
            }
        }
    );
}
