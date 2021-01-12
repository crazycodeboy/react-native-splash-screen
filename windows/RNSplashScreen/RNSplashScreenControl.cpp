#include "pch.h"
#include "RNSplashScreenControl.h"
#if __has_include("RNSplashScreenControl.g.cpp")
#include "RNSplashScreenControl.g.cpp"
#endif

using namespace winrt;
using namespace Windows::UI;
using namespace Windows::UI::Xaml;
using namespace Windows::UI::Xaml::Media;
using namespace Windows::Foundation;
using namespace Windows::Data::Xml::Dom;

namespace winrt::RNSplashScreen::implementation
{
    IAsyncAction RNSplashScreenControl::LoadSplashScreenInformation()
    {
        winrt::apartment_context ui_thread; // Capture ui calling context;

        Windows::Storage::StorageFile appxmanifestFile = co_await Windows::ApplicationModel::Package::Current().InstalledLocation().GetFileAsync(L"AppxManifest.xml");
        XmlDocument appxmanifestXML = co_await XmlDocument::LoadFromFileAsync(appxmanifestFile);
        auto uapNamespace = winrt::box_value(L"xmlns:uap='http://schemas.microsoft.com/appx/manifest/uap/windows10'");

        IXmlNode visualElementXml = appxmanifestXML.SelectSingleNodeNS(L"//uap:VisualElements", uapNamespace);
        std::string visualBackgroundValue = "";
        {
            IXmlNode backgroundNode;
            if ((backgroundNode = visualElementXml.Attributes().GetNamedItem(L"BackgroundColor")) != nullptr)
            {
                visualBackgroundValue = winrt::to_string(winrt::unbox_value_or<hstring>(backgroundNode.NodeValue(), L""));
            }
        }

        IXmlNode splashScreenXml = visualElementXml.SelectSingleNodeNS(L"//uap:SplashScreen", uapNamespace);
        std::string splashScreenBackgroundValue = "";
        {
            IXmlNode backgroundNode;
            if ((backgroundNode = splashScreenXml.Attributes().GetNamedItem(L"BackgroundColor")) != nullptr)
            {
                splashScreenBackgroundValue = winrt::to_string(winrt::unbox_value_or<hstring>(backgroundNode.NodeValue(), L""));
            }
        }

        std::string splashScreenImageValue = "";
        {
            IXmlNode imageNode;
            if ((imageNode = splashScreenXml.Attributes().GetNamedItem(L"Image")) != nullptr)
            {
                splashScreenImageValue = winrt::to_string(winrt::unbox_value_or<hstring>(imageNode.NodeValue(), L""));
            }
        }

        co_await ui_thread; // Switch back to UI thread;

        Uri splashImageUri = Uri(winrt::to_hstring("ms-appx:///" + splashScreenImageValue));

        if (splashScreenBackgroundValue.size() > 0)
        {
            UpdateSplashScreenVisuals(splashScreenBackgroundValue, splashImageUri);
        } else if (visualBackgroundValue.size() > 0)
        {
            UpdateSplashScreenVisuals(visualBackgroundValue, splashImageUri);
        } else
        {
            UpdateSplashScreenVisuals("", splashImageUri);
        }
    }

    RNSplashScreenControl::RNSplashScreenControl()
    {
        InitializeComponent();
        IAsyncAction loadSplashScreenInfoAction = LoadSplashScreenInformation();
        loadSplashScreenInfoAction.Completed([](auto, auto)
            {
            }
        );
    }

    void RNSplashScreenControl::UpdateSplashScreenVisuals(std::string backgroundColor, Uri uri)
    {
        auto actualColor = ParseColor(backgroundColor);
        if (actualColor.has_value())
        {
            BackgroundGrid().Background(SolidColorBrush(actualColor.value()));
        }
        if (uri != nullptr)
        {
            SplashImage().Source(Windows::UI::Xaml::Media::Imaging::BitmapImage(uri));
        }
    }

    std::optional<Windows::UI::Color> RNSplashScreenControl::ParseColor(std::string color)
    {
        if (color[0] == '#' && color.size() == 7)
        {
            Color result;
            result.R = std::stoi(color.substr(1, 2), 0, 16);
            result.G = std::stoi(color.substr(3, 2), 0, 16);
            result.B = std::stoi(color.substr(5, 2), 0, 16);
            result.A = 0xff;
            return result;
        } else if (color[0] == '#' && color.size() == 9)
        {
            Color result;
            result.A = std::stoi(color.substr(1, 2), 0, 16);
            result.R = std::stoi(color.substr(3, 2), 0, 16);
            result.G = std::stoi(color.substr(5, 2), 0, 16);
            result.B = std::stoi(color.substr(7, 2), 0, 16);
            return result;
        } else
        {
            // lower case
            std::transform(color.begin(), color.end(), color.begin(),
                [](unsigned char c) { return std::tolower(c); });

            // map all known names into correspondent color
            if (color == "aliceblue") return Colors::AliceBlue();
            if (color == "antiquewhite") return Colors::AntiqueWhite();
            if (color == "aqua") return Colors::Aqua();
            if (color == "aquamarine") return Colors::Aquamarine();
            if (color == "azure") return Colors::Azure();
            if (color == "beige") return Colors::Beige();
            if (color == "bisque") return Colors::Bisque();
            if (color == "black") return Colors::Black();
            if (color == "blanchedalmond") return Colors::BlanchedAlmond();
            if (color == "blue") return Colors::Blue();
            if (color == "blueviolet") return Colors::BlueViolet();
            if (color == "brown") return Colors::Brown();
            if (color == "burlywood") return Colors::BurlyWood();
            if (color == "cadetblue") return Colors::CadetBlue();
            if (color == "chartreuse") return Colors::Chartreuse();
            if (color == "chocolate") return Colors::Chocolate();
            if (color == "coral") return Colors::Coral();
            if (color == "cornflowerblue") return Colors::CornflowerBlue();
            if (color == "cornsilk") return Colors::Cornsilk();
            if (color == "crimson") return Colors::Crimson();
            if (color == "cyan") return Colors::Cyan();
            if (color == "darkblue") return Colors::DarkBlue();
            if (color == "darkcyan") return Colors::DarkCyan();
            if (color == "darkgoldenrod") return Colors::DarkGoldenrod();
            if (color == "darkgray") return Colors::DarkGray();
            if (color == "darkgreen") return Colors::DarkGreen();
            if (color == "darkkhaki") return Colors::DarkKhaki();
            if (color == "darkmagenta") return Colors::DarkMagenta();
            if (color == "darkolivegreen") return Colors::DarkOliveGreen();
            if (color == "darkorange") return Colors::DarkOrange();
            if (color == "darkorchid") return Colors::DarkOrchid();
            if (color == "darkred") return Colors::DarkRed();
            if (color == "darksalmon") return Colors::DarkSalmon();
            if (color == "darkseagreen") return Colors::DarkSeaGreen();
            if (color == "darkslateblue") return Colors::DarkSlateBlue();
            if (color == "darkslategray") return Colors::DarkSlateGray();
            if (color == "darkturquoise") return Colors::DarkTurquoise();
            if (color == "darkviolet") return Colors::DarkViolet();
            if (color == "deeppink") return Colors::DeepPink();
            if (color == "deepskyblue") return Colors::DeepSkyBlue();
            if (color == "dimgray") return Colors::DimGray();
            if (color == "dodgerblue") return Colors::DodgerBlue();
            if (color == "firebrick") return Colors::Firebrick();
            if (color == "floralwhite") return Colors::FloralWhite();
            if (color == "forestgreen") return Colors::ForestGreen();
            if (color == "fuchsia") return Colors::Fuchsia();
            if (color == "gainsboro") return Colors::Gainsboro();
            if (color == "ghostwhite") return Colors::GhostWhite();
            if (color == "gold") return Colors::Gold();
            if (color == "goldenrod") return Colors::Goldenrod();
            if (color == "gray") return Colors::Gray();
            if (color == "green") return Colors::Green();
            if (color == "greenyellow") return Colors::GreenYellow();
            if (color == "honeydew") return Colors::Honeydew();
            if (color == "hotpink") return Colors::HotPink();
            if (color == "indianred") return Colors::IndianRed();
            if (color == "indigo") return Colors::Indigo();
            if (color == "ivory") return Colors::Ivory();
            if (color == "khaki") return Colors::Khaki();
            if (color == "lavender") return Colors::Lavender();
            if (color == "lavenderblush") return Colors::LavenderBlush();
            if (color == "lawngreen") return Colors::LawnGreen();
            if (color == "lemonchiffon") return Colors::LemonChiffon();
            if (color == "lightblue") return Colors::LightBlue();
            if (color == "lightcoral") return Colors::LightCoral();
            if (color == "lightcyan") return Colors::LightCyan();
            if (color == "lightgoldenrodyellow") return Colors::LightGoldenrodYellow();
            if (color == "lightgreen") return Colors::LightGreen();
            if (color == "lightgray") return Colors::LightGray();
            if (color == "lightpink") return Colors::LightPink();
            if (color == "lightsalmon") return Colors::LightSalmon();
            if (color == "lightseagreen") return Colors::LightSeaGreen();
            if (color == "lightskyblue") return Colors::LightSkyBlue();
            if (color == "lightslategray") return Colors::LightSlateGray();
            if (color == "lightsteelblue") return Colors::LightSteelBlue();
            if (color == "lightyellow") return Colors::LightYellow();
            if (color == "lime") return Colors::Lime();
            if (color == "limegreen") return Colors::LimeGreen();
            if (color == "linen") return Colors::Linen();
            if (color == "magenta") return Colors::Magenta();
            if (color == "maroon") return Colors::Maroon();
            if (color == "mediumaquamarine") return Colors::MediumAquamarine();
            if (color == "mediumblue") return Colors::MediumBlue();
            if (color == "mediumorchid") return Colors::MediumOrchid();
            if (color == "mediumpurple") return Colors::MediumPurple();
            if (color == "mediumseagreen") return Colors::MediumSeaGreen();
            if (color == "mediumslateblue") return Colors::MediumSlateBlue();
            if (color == "mediumspringgreen") return Colors::MediumSpringGreen();
            if (color == "mediumturquoise") return Colors::MediumTurquoise();
            if (color == "mediumvioletred") return Colors::MediumVioletRed();
            if (color == "midnightblue") return Colors::MidnightBlue();
            if (color == "mintcream") return Colors::MintCream();
            if (color == "mistyrose") return Colors::MistyRose();
            if (color == "moccasin") return Colors::Moccasin();
            if (color == "navajowhite") return Colors::NavajoWhite();
            if (color == "navy") return Colors::Navy();
            if (color == "oldlace") return Colors::OldLace();
            if (color == "olive") return Colors::Olive();
            if (color == "olivedrab") return Colors::OliveDrab();
            if (color == "orange") return Colors::Orange();
            if (color == "orangered") return Colors::OrangeRed();
            if (color == "orchid") return Colors::Orchid();
            if (color == "palegoldenrod") return Colors::PaleGoldenrod();
            if (color == "palegreen") return Colors::PaleGreen();
            if (color == "paleturquoise") return Colors::PaleTurquoise();
            if (color == "palevioletred") return Colors::PaleVioletRed();
            if (color == "papayawhip") return Colors::PapayaWhip();
            if (color == "peachpuff") return Colors::PeachPuff();
            if (color == "peru") return Colors::Peru();
            if (color == "pink") return Colors::Pink();
            if (color == "plum") return Colors::Plum();
            if (color == "powderblue") return Colors::PowderBlue();
            if (color == "purple") return Colors::Purple();
            if (color == "red") return Colors::Red();
            if (color == "rosybrown") return Colors::RosyBrown();
            if (color == "royalblue") return Colors::RoyalBlue();
            if (color == "saddlebrown") return Colors::SaddleBrown();
            if (color == "salmon") return Colors::Salmon();
            if (color == "sandybrown") return Colors::SandyBrown();
            if (color == "seagreen") return Colors::SeaGreen();
            if (color == "seashell") return Colors::SeaShell();
            if (color == "sienna") return Colors::Sienna();
            if (color == "silver") return Colors::Silver();
            if (color == "skyblue") return Colors::SkyBlue();
            if (color == "slateblue") return Colors::SlateBlue();
            if (color == "slategray") return Colors::SlateGray();
            if (color == "snow") return Colors::Snow();
            if (color == "springgreen") return Colors::SpringGreen();
            if (color == "steelblue") return Colors::SteelBlue();
            if (color == "tan") return Colors::Tan();
            if (color == "teal") return Colors::Teal();
            if (color == "thistle") return Colors::Thistle();
            if (color == "tomato") return Colors::Tomato();
            if (color == "transparent") return Colors::Transparent();
            if (color == "turquoise") return Colors::Turquoise();
            if (color == "violet") return Colors::Violet();
            if (color == "wheat") return Colors::Wheat();
            if (color == "white") return Colors::White();
            if (color == "whitesmoke") return Colors::WhiteSmoke();
            if (color == "yellow") return Colors::Yellow();
            if (color == "yellowgreen") return Colors::YellowGreen();
        }
        return std::optional<Windows::UI::Color>();
    }

}
