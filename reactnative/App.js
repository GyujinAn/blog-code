import React from 'react';
import UploadStack from './practice/upload/UploadStack';
import BorderedInput from './components/BorderedInput';
import {Button} from 'react-native';
import {createPost} from './lib/posts';
import ScrollHideComponent from './practice/animated/ScrollHideComponent';
import ComponentsForLayOut from './practice/layout/ComponentForLayOut';

import ProfileScreen from './practice/profile/screens/ProfileScreen';

const testCreatePost = async () => {
  console.log('testCreatePost is started');
  const org = {
    hello: 'world',
  };
  await createPost({org});
};

function App() {
  return <ComponentsForLayOut />;
}
export default App;
