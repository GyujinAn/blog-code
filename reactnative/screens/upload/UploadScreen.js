import React, {useEffect, useState} from 'react';
import {Pressable, ScrollView, Text, TextInput, View} from 'react-native';
import DateTimePickerModal from 'react-native-modal-datetime-picker';
import Icon from 'react-native-vector-icons/MaterialIcons';
import PhotoInputButton from '../../components/PhotoInputButton';
import IconRightButton from '../../components/IconRightButton';

function UploadScreen({navigation, route}) {
  const [title, setTitle] = useState('');
  const [descriptio, setDescription] = useState('');
  const [time, setTime] = useState(new Date());
  const [location, setLocation] = useState('');
  const [visibleTimePickerModal, setVisibleTimePickerModal] = useState(false);
  const [form, setForm] = useState({
    time: '',
    location: '',
  });

  useEffect(() => {
    setLocation(route.params?.location);
  }, [navigation, route.params]);

  useEffect(() => {
    navigation.setOptions({
      headerRight: () => (
        <IconRightButton
          onPress={() => {
            console.log('here is submit');
            console.log(time);
            console.log(location);
          }}
          name="send"
        />
      ),
    });
  }, [navigation, time, location]);

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
        <Pressable onPress={() => setVisibleTimePickerModal(true)}>
          <Text>시간</Text>
          <Icon name="access-time" color="black" size={24} />
        </Pressable>
        <DateTimePickerModal
          isVisible={visibleTimePickerModal}
          mode="datetime"
          date={time}
          // onConfirm={setTime}
          onConfirm={() => {
            setForm(form => {
              ({...form, time});
            });
          }}
          onCancel={() => setVisibleTimePickerModal(false)}
        />
        <Pressable onPress={() => navigation.push('LocationInputScreen', {})}>
          <Text>위치</Text>
          <Icon name="location-pin" color="black" size={24} />
        </Pressable>
        <PhotoInputButton />
      </View>
    </View>
  );
}

export default UploadScreen;
