import React from 'react';
import {StyleSheet, View} from 'react-native';
import {GooglePlacesAutocomplete} from 'react-native-google-places-autocomplete';
import Config from 'react-native-config';

function GooglePlacesInput({onPress}) {
  return (
    <View style={styles.container}>
      <GooglePlacesAutocomplete
        placeholder="Search"
        onPress={(data, details = null) => {
          const location = {
            place_id: data.place_id,
            description: data.description,
          };
          onPress(location);
        }}
        query={{
          key: Config.GOOGLE_API_KEY,
          language: 'ko',
        }}
        onFail={error => console.error(error)}
        onNotFound={() => console.log('no results')}
      />
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    padding: 10,
    paddingTop: 10,
    backgroundColor: '#ecf0f1',
  },
});

export default GooglePlacesInput;
