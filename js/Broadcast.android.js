import React, {Component} from 'react';
import {
    Platform,
    StyleSheet,
    Text,
    View,
    NativeModules,
    Dimensions,
    Image,
    TouchableOpacity
} from 'react-native';
import PropTypes from 'prop-types';

import ABView from './ABView'

const ABModule = NativeModules.ABModule;

export default class Broadcast extends Component<{}> {

    constructor(props) {
        super(props);
    }

    componentDidMount = () => {
        const {started, videoEnabled, audioEnabled, url, cameraFronted} = this.props;
        if (started && url) {
            this.startPublish(url)
        }
        if (videoEnabled) {
            ABModule.enableVideo();
        } else {
            ABModule.disableVideo();
        }

        if (audioEnabled) {
            ABModule.enableAudio();
        } else {
            ABModule.disableAudio();
        }
        if (!cameraFronted) {
            ABModule.switchCamera();
        }
    };

    componentDidUpdate = (prevProps, prevState) => {
        const {started, videoEnabled, audioEnabled, url, cameraFronted} = this.props;
        if (started !== prevProps.started || url !== prevProps.url) {
            if (started) {
                this.startPublish(url);
            } else {
                this.stopPublish();
            }
        }

        if (cameraFronted !== prevProps.cameraFronted) {
            ABModule.switchCamera();
        }

        if (videoEnabled) {
            ABModule.enableVideo();
        } else {
            ABModule.disableVideo();
        }

        if (audioEnabled) {
            ABModule.enableAudio();
        } else {
            ABModule.disableAudio();
        }
    };

    startPublish = async (rtmpUrl) => {
        return await ABModule.startStream(rtmpUrl);
    };

    stopPublish = async () => {
        return await ABModule.stopStream();
    };

    switchCamera = async () => {
        return await ABModule.switchCamera();
    };

    render() {
        const {style} = this.props;
        return (
            <ABView style={style}/>
        );
    }
}

Broadcast.propTypes = {
    started: PropTypes.bool,
    cameraFronted: PropTypes.bool,
    videoEnabled: PropTypes.bool,
    audioEnabled: PropTypes.bool,
    url: PropTypes.string,
    ...View.propTypes,
};

Broadcast.defaultProps= {
    cameraFronted: true
};


const styles = StyleSheet.create({

});
