import React from 'react';
import {View, Text, StyleSheet, Button} from 'react-native';

function DetailScreen({route, navigation}) {
  return (
    <View style={styles.block}>
      <Text style={styles.text}>id: {route.params.id}</Text>
      <View style={styles.button}>
        <Button
          title="next"
          onPress={() =>
            // navigation.push('Detail', {id: route.params.id + 1})
            navigation.push('Detail', {id: route.params.id + 1})
          }></Button>
        <Button title="back" onPress={() => navigation.pop()} />
        <Button title="goToFirst" onPress={() => navigation.popToTop()} />
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  block: {
    flex: 1,
    alignItems: 'center',
    justifyContent: 'center',
  },
  text: {
    fontSize: 48,
  },
  button: {
    flexDirection: 'row',
  },
});

export default DetailScreen;
