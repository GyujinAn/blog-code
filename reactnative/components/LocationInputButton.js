import React from 'react';
import {Pressable, Text, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

function LocationInputButton() {
  return (
    <View>
      <Pressable>
        <Text>위치</Text>
        <Icon name="location-pin" color="black" size={24} />
      </Pressable>
    </View>
  );
}

export default LocationInputButton;
