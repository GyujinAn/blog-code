import React from 'react';
import {Image, Pressable, StyleSheet, Text, View} from 'react-native';

function OvalButton({active, name, icon, onPress}) {
  return (
    <Pressable
      style={[styles.root, !active && {opacity: 0.3}]}
      onPress={onPress}>
      {active && <Image source={require('../assets/oval_copy.png')} />}
      <View style={styles.iconTextContainer}>
        <Image source={icon} style={styles.icon} />
        <Text style={styles.text}>{name}</Text>
      </View>
    </Pressable>
  );
}

const styles = StyleSheet.create({
  root: {
    // backgroundColor: '#aae50ac2',
    width: 120,
    height: '100%',
    alignItems: 'center',
    justifyContent: 'center',
  },
  iconTextContainer: {
    position: 'absolute',
    alignItems: 'center',
    justifyContent: 'center',
  },
  icon: {
    marginTop: 24,
  },
  text: {
    width: 120,
    height: 22,
    fontSize: 16,
    textAlign: 'center',
    marginTop: 4,
    color: '#333333',
  },
});

export default OvalButton;
