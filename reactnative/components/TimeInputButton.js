import React from 'react';
import {Pressable, Text, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

function TimeInputButton() {
  return (
    <View>
      <Pressable>
        <Text>시간</Text>
        <Icon name="access-time" color="black" size={24} />
      </Pressable>
    </View>
  );
}

export default TimeInputButton;
