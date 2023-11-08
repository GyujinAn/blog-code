import React, {useState} from 'react';
import {Button, SafeAreaView} from 'react-native';
import Greeting from './components/Greeting';
import Box from './components/Box';

const App = () => {
  const [visible, setVisible] = useState(true);
  const onPress = () => {
    setVisible(!visible);
  };
  return (
    <SafeAreaView>
      <Greeting />
      <Button title="button" onPress={onPress} />
      {visible && <Box rounded={true} size="large" color="blue" />}
    </SafeAreaView>
  );
};

export default App;
