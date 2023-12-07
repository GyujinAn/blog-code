import React from 'react';
import {Image, Pressable} from 'react-native';

function SettingIcon({onPress}) {
  return (
    <Pressable onPress={onPress}>
      <Image source={require('../assets/setting.png')} size={24} />
    </Pressable>
  );
}

export default SettingIcon;
