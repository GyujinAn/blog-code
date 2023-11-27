import React from 'react';
import {Button, View} from 'react-native';

function BScreen({navigation}) {
  return (
    <View>
      <Button title="BBB" onPress={() => navigation.navigate('A')} />
    </View>
  );
}

export default BScreen;
