/**
 * SplashScreen
 * 启动屏
 * from：http://www.devio.org
 * Author:CrazyCodeBoy
 * GitHub:https://github.com/crazycodeboy
 * Email:crazycodeboy@gmail.com
 * @flow
 */
'use strict';


import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    TouchableOpacity,
    Linking,
    Button,
    Platform,
} from 'react-native'
import SplashScreen from 'react-native-splash-screen'

export default class example extends Component {

    componentDidMount() {
        SplashScreen.hide();
    }

    render() {
        return (
            <View style={styles.container}>
                <TouchableOpacity
                    onPress={(e)=> {
                        Linking.openURL('https://coding.imooc.com/class/304.html');
                    }}
                >
                    <View >
                        <Text style={styles.item}>
                            SplashScreen 启动屏
                        </Text>
                        <Text style={styles.item}>
                            @：http://www.devio.org/
                        </Text>
                        <Text style={styles.item}>
                            GitHub:https://github.com/crazycodeboy
                        </Text>
                        <Text style={styles.item}>
                            Email:crazycodeboy@gmail.com
                        </Text>
                    </View>
                </TouchableOpacity>
                {
                    // Show splash screen again on Windows and Android, for 3s.
                    (Platform.OS === 'windows' || Platform.OS === 'android') &&
                    <View style={styles.showSplashButtonView}>
                        <Button
                            title={"Show splash screen again"}
                            testID={"ShowSplashScreenButton"}
                            onPress={()=> {SplashScreen.show(); setTimeout(()=> SplashScreen.hide(), 3000)}}
                        />
                    </View>
                }
            </View>
        )
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#f3f2f2',
        paddingTop: 30,
        paddingBottom: 30,
        paddingLeft: 30,
        paddingRight: 30,
    },
    item: {
        fontSize: 20,
    },
    line: {
        flex: 1,
        height: 0.3,
        backgroundColor: 'darkgray',
    },
    showSplashButtonView: {
        marginTop:30,
        alignSelf: 'baseline',
    }
})
