import React, {useState} from 'react';
import {ScrollView, TextInput, View} from 'react-native';
import LocationInputButton from '../../components/LocationInputButton';
import PhotoInputButton from '../../components/PhotoInputButton';
import TimeInputButton from '../../components/TimeInputButton';

function UploadScreen() {
  const [title, setTitle] = useState('');
  const [descriptio, setDescription] = useState('');
  return (
    <View>
      <ScrollView>
        <TextInput
          placeholder="제목을 적어주세요."
          value={title}
          onChangeText={setTitle}
        />
        <TextInput
          placeholder="자세한 설명을 적어주세요."
          value={descriptio}
          onChangeText={setDescription}
          multiline={true}
        />
      </ScrollView>
      <View>
        <TimeInputButton />
        <LocationInputButton />
        <PhotoInputButton />
      </View>
    </View>
  );
}

export default UploadScreen;
