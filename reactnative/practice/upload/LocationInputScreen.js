import React from 'react';
import GooglePlacesInput from '../../components/GooglePlacesInput';

function LocationInputScreen({navigation}) {
  return (
    <GooglePlacesInput
      onPress={location => {
        navigation.navigate('UploadScreen', {location: location});
      }}
    />
  );
}

export default LocationInputScreen;
