import React from 'react';
import {StatusBar, View} from 'react-native';
import {Text} from 'react-native';
import {StyleSheet} from 'react-native';

function DateHead({date}) {
  const year = date.getFullYear();
  const month = date.getMonth() + 1;
  const day = date.getDate();
  const formatted = `${year}-${month}-${day}`;
  return (
    <>
      <StatusBar backgroundColor="#26a69a"></StatusBar>
      <View style={styles.block}>
        <Text style={styles.dateText}> {formatted} </Text>
      </View>
    </>
  );
}

const styles = StyleSheet.create({
  block: {
    padding: 16,
    backgroundColor: '#26a69a',
  },

  dateText: {
    fontSize: 24,
    color: 'white',
  },
});

export default DateHead;
