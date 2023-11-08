import React from 'react';
import {View, Text} from 'react-native';

function Greeting(props) {
  return (
    <>
      <View>
        <Text>Hello {props.name}!</Text>
      </View>
      <Text>second statement</Text>
    </>
  );
}

Greeting.defaultProps = {
  name: 'Bob',
};

export default Greeting;
