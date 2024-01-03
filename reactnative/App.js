import React from 'react';
import UploadStack from './practice/upload/UploadStack';
import BorderedInput from './components/BorderedInput';
import {Button} from 'react-native';
import {createPost} from './lib/posts';
import ScrollHideComponent from './practice/animated/ScrollHideComponent';
import ComponentsForLayOut from './practice/layout/ComponentForLayOut';

const testCreatePost = async () => {
  console.log('testCreatePost is started');
  const org = {
    hello: 'world',
  };
  await createPost({org});
};

function App() {
  // return <UploadStack />;
  // return <BorderedInput />;
  // return <Button title="Press me" onPress={testCreatePost} />;
  // return <ScrollHideComponent />;
  return <ComponentsForLayOut />;
}
export default App;
