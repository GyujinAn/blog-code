import {NavigationContainer} from '@react-navigation/native';
import {createNativeStackNavigator} from '@react-navigation/native-stack';
import React from 'react';
import AScreen from './AScreen';
import BScreen from './BScreen';

const Stack = createNativeStackNavigator();

function SampleStack() {
  console.log('start SampleStack');
  return (
    <NavigationContainer>
      <Stack.Navigator initialRouteName="A">
        <Stack.Screen name="A" component={AScreen} />
        <Stack.Screen name="B" component={BScreen} />
      </Stack.Navigator>
    </NavigationContainer>
  );
}

export default SampleStack;
