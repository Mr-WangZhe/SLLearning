/**
 * Sample React Native App
 * https://github.com/facebook/react-native
 * @flow
 */

import React, { Component } from 'react';
import {
  Platform,
  StyleSheet,
  Text,
  View,
  Image,
  ListView,
  TouchableOpacity
  }from 'react-native';
import {Navigator} from 'react-native-deprecated-custom-components';
import FenLei_Y from './FenLei_Y'
import FenLei_R from './FenLei_R'
import FenLei_F from './FenLei_F'
/*导入外部组件类*/
export default class App extends Component<Props> {
  render() {  
    return (
            <View> 
              <TouchableOpacity onPress={this.tiaozhuan.bind(this)}>  
               <View style={{borderWidth:1,height:80,width:360,backgroundColor:'#AEEEEE',marginLeft:20,marginTop:30,borderRadius:70}}>    
                    <Text style={styles.content}>英语</Text>
               </View>
              </TouchableOpacity>
              <TouchableOpacity onPress={this.tiaozhuana.bind(this)}>  
               <View style={{borderWidth:1,height:80,width:360,backgroundColor:'#AEEEEE',marginLeft:20,marginTop:30,borderRadius:70}}>    
                    <Text style={styles.content}>日语</Text>
               </View>
              </TouchableOpacity>
              <TouchableOpacity onPress={this.tiaozhuanb.bind(this)}>  
               <View style={{borderWidth:1,height:80,width:360,backgroundColor:'#AEEEEE',marginLeft:20,marginTop:30,borderRadius:70}}>    
                    <Text style={styles.content}>法语</Text>
               </View>
              </TouchableOpacity>
            </View>          
    );
  }
  tiaozhuan(){
    const navigator = this.props.navigator;
    navigator.push({
      component:FenLei_Y
    })
  }
   tiaozhuana(){
    const navigator = this.props.navigator;
    navigator.push({
      component:FenLei_R
    })
  }
   tiaozhuanb(){
    const navigator = this.props.navigator;
    navigator.push({
      component:FenLei_F
    })
  }
}

const styles = StyleSheet.create({
  content: {
        fontSize: 20,
        color: 'white',
        fontWeight:'bold',
        textAlign:'center',
        marginTop:20,
    },

});

