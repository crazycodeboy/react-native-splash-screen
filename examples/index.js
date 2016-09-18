/**
 * SplashScreen
 * 启动屏
 * 出自：http://www.cboy.me
 * GitHub:https://github.com/crazycodeboy
 * Eamil:crazycodeboy@gmail.com
 * @flow
 * */
'use strict';


import React, {Component} from 'react';
import {
    StyleSheet,
    View,
    Text,
    TouchableOpacity,
    Linking,
} from 'react-native'
import SplashScreen from 'react-native-splash-screen'

export default class example extends Component {

    componentDidMount() {
        SplashScreen.hide();
    }


    render() {
        return (
            <TouchableOpacity
                style={styles.container}
                onPress={(e)=> {
                    Linking.openURL('http://www.cboy.me');
                }}
            >
                <View >
                    <Text style={styles.item}>
                        SplashScreen 启动屏
                    </Text>
                    <Text style={styles.item}>
                        @：http://www.cboy.me
                    </Text>
                    <Text style={styles.item}>
                        GitHub:https://github.com/crazycodeboy
                    </Text>
                    <Text style={styles.item}>
                        Eamil:crazycodeboy@gmail.com
                    </Text>
                </View>
            </TouchableOpacity>
        )
    }

}

const styles = StyleSheet.create({
    container: {
        flex: 1,
        backgroundColor: '#f3f2f2',
        marginTop: 30
    },
    item: {
        fontSize: 20,
    },
    line: {
        flex: 1,
        height: 0.3,
        backgroundColor: 'darkgray',
    },
})