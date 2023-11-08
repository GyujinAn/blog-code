import React from 'react';
import {StyleSheet, View} from 'react-native';

function Box({rounded, size, color}) {
  return (
    <View
      style={[
        styles.box,
        rounded && styles.round,
        sizes[size],
        {
          backgroundColor: color,
        },
      ]}></View>
  );
}

Box.defaultProps = {
  size: 'medium',
  color: 'black',
};

const styles = StyleSheet.create({
  box: {
    backgroundColor: 'black',
  },

  round: {
    borderRadius: 16,
  },

  small: {
    width: 32,
    height: 32,
  },

  medium: {
    width: 64,
    height: 64,
  },

  large: {
    width: 128,
    height: 128,
  },
});

const sizes = {
  small: styles.small,
  medium: styles.medium,
  large: styles.large,
};

export default Box;
