import {useNavigation} from '@react-navigation/native';
import React from 'react';
import {Pressable, Text, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

function LocationInputButton() {
  const navigation = useNavigation();
  return (
    <View>
      <Pressable onPress={() => navigation.push('LocationInputScreen')}>
        <Text>위치</Text>
        <Icon name="location-pin" color="black" size={24} />
      </Pressable>
    </View>
  );
}

export default LocationInputButton;
