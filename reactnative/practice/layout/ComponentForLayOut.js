import React, {useState} from 'react';
import {View, Text, SafeAreaView} from 'react-native';

const ComponentForLayOut = () => {
  const [position, setPosition] = useState({x: 0, y: 0});

  const handleLayout = event => {
    const {x, y} = event.nativeEvent.layout;
    setPosition({x, y});
  };

  return (
    <SafeAreaView>
      {/* <Text>Component Content</Text>
      <Text>Component Content</Text>
      <Text>Component Content</Text>
      <Text>Component Content</Text>
      <Text>Component Content</Text> */}
      <View onLayout={handleLayout}>
        <Text>Y Position: {position.y}</Text>
      </View>
    </SafeAreaView>
  );
};

export default ComponentForLayOut;
