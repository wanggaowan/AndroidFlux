# AndroidFlux
   用于Android开发的flux模式

   * [项目github地址](https://github.com/wanggaowan/AndroidFlux)

   [![License](https://img.shields.io/badge/license-Apache%202-4EB1BA.svg)](https://www.apache.org/licenses/LICENSE-2.0.html)
   [![](https://jitpack.io/v/wanggaowan/AndroidFlux.svg)](https://jitpack.io/#wanggaowan/AndroidFlux)

   #### 如何使用：
   1. 添加 JitPack repository到你的build文件
      ```groovy
       allprojects {
           repositories {
               maven { url 'https://www.jitpack.io' }
           }
       }
      ```

   2. 增加依赖
      ```groovy
      dependencies {
         implementation 'com.github.wanggaowan:AndroidFlux:1.0'
      }
      ```

   3. 由于AndroidFlux 使用了java8，所以需要在app.gradle中android里添加
      ```groovy
      compileOptions {
          sourceCompatibility 1.8
          targetCompatibility 1.8
      }
      ```

   #### Proguard
   无需添加任何混淆规则，可直接混淆

   #### *License*
   AndroidFlux is released under the Apache 2.0 license.
   ```
   Copyright 2019 wanggaowan.
   
   Licensed under the Apache License, Version 2.0 (the "License");
   you may not use this file except in compliance with the License.
   You may obtain a copy of the License at following link.
   
        http://www.apache.org/licenses/LICENSE-2.0
   
   Unless required by applicable law or agreed to in writing, software
   distributed under the License is distributed on an "AS IS" BASIS,
   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
   See the License for the specific language governing permissions and
   limitat
   ```