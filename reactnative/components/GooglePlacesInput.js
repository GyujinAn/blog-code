import React from 'react';
import {StyleSheet, View} from 'react-native';
import {GooglePlacesAutocomplete} from 'react-native-google-places-autocomplete';

function GooglePlacesInput() {
  const API_KEY = '';
  return (
    <View style={styles.container}>
      <GooglePlacesAutocomplete
        placeholder="Search"
        onPress={(data, details = null) => {
          // 'details' is provided when fetchDetails = true
          console.log(data, details);
        }}
        query={{
          key: API_KEY,
          language: 'en',
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
