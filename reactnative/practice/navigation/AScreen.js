import React from 'react';
import {Button, View} from 'react-native';

function AScreen({navigation}) {
  return (
    <View>
      <Button title="AAA" onPress={() => navigation.navigate('B')} />
    </View>
  );
}

export default AScreen;
