import React, {Component} from 'react'
import {requireNativeComponent, View, StyleSheet} from 'react-native'

const iface = {
    name: 'ABView',
    propTypes: {
        ...View.propTypes
    },
};

const ABView = requireNativeComponent('ABView', iface);

const styles = StyleSheet.create({
    text: {
        color: 'green'
    }
});

export default ABView


