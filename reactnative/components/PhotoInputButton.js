import React from 'react';
import {Pressable, Text, View} from 'react-native';
import Icon from 'react-native-vector-icons/MaterialIcons';

function PhotoInputButton() {
  return (
    <View>
      <Pressable>
        <Text>사진</Text>
        <Icon name="image" color="black" size={24} />
      </Pressable>
    </View>
  );
}

export default PhotoInputButton;
