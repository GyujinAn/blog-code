import React, {useState, useEffect} from 'react';
import {View, ScrollView, Animated, Text, SafeAreaView} from 'react-native';

const ScrollHideComponent = () => {
  const scrollY = new Animated.Value(0);
  const [isVisible, setIsVisible] = useState(true);

  const handleScroll = Animated.event(
    [{nativeEvent: {contentOffset: {y: scrollY}}}],
    {useNativeDriver: false}, // Ensure this is set to false for android support
  );

  useEffect(() => {
    const hideComponentThreshold = 100; // Adjust this value based on your preference

    scrollY.addListener(({value}) => {
      console.log(`value: ${value}`);
      setIsVisible(value < hideComponentThreshold);
    });

    return () => {
      scrollY.removeAllListeners();
    };
  }, [scrollY]);

  useEffect(() => {
    console.log(`isVisible: ${isVisible}`);
  }, [isVisible]);

  return (
    <SafeAreaView style={{flex: 1}}>
      <ScrollView
        onScroll={handleScroll}
        scrollEventThrottle={16} // Adjust the throttle value based on your needs
      >
        <Text style={{fontSize: 150}}>Hello</Text>
        <Text style={{fontSize: 150}}>Hello</Text>
        <Text style={{fontSize: 150}}>Hello</Text>
        <Text style={{fontSize: 150}}>Hello</Text>
        <Text style={{fontSize: 150}}>Hello</Text>
        <Text style={{fontSize: 150}}>Hello</Text>
      </ScrollView>

      {isVisible && (
        <Animated.View
          style={{
            position: 'absolute',
            bottom: 0,
            left: 0,
            right: 0,
            height: 50, // Adjust the height based on your component's size
            backgroundColor: 'yellow', // Optional: Add a background color
            justifyContent: 'center',
            alignItems: 'center',
          }}>
          {/* Your hideable component content */}
        </Animated.View>
      )}
    </SafeAreaView>
  );
};

export default ScrollHideComponent;
