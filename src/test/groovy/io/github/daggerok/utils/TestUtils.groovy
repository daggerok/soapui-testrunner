/**
 * The MIT License
 *
 * Copyright 2017 Maksim Kostromin
 *
 * Permission is hereby granted, free of charge, to any person
 * obtaining a copy of this software and associated documentation files
 * (the "Software"), to deal in the Software without restriction,
 * including without limitation the rights to use, copy, modify, merge,
 * publish, distribute, sublicense, and/or sell copies of the Software,
 * and to permit persons to whom the Software is furnished to do so,
 * subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be
 * included in all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
 * EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
 * MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
 * IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
 * CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
 * TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
 * SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
 *
 * https://tldrlegal.com/license/mit-license
 */
package io.github.daggerok.utils

import groovy.transform.CompileStatic

@CompileStatic
class TestUtils {

  static getGradleBuildHead(final String pluginVersion) {
    return usePlugins(pluginVersion)
//    return useApplyPlugin(pluginVersion, true)
//    return useApplyPlugin(pluginVersion, false)
  }

  private static usePlugins(final String pluginVersion) {
    """
    buildscript {
      repositories {
        maven { url 'https://plugins.gradle.org/m2/' }
        maven { url 'http://smartbearsoftware.com/repository/maven2/' }
      }
    }
    
    plugins {
      id 'io.github.daggerok.soapui-runner' version '$pluginVersion'
    }
    """
  }

  private static useApplyPlugin(final String pluginVersion, final boolean isLocal) {
    """
    buildscript {
      repositories {
        ${isLocal ? 'mavenLocal()' : ''}
        maven { url 'https://plugins.gradle.org/m2/' }
        maven { url 'http://smartbearsoftware.com/repository/maven2/' }
      }
      dependencies {
        classpath '${isLocal ? '' : 'gradle.plugin.'}io.github.daggerok:soapui-runner:$pluginVersion'
      }
    }
    apply plugin: 'io.github.daggerok.soapui-runner'
    """
  }

  private TestUtils() {}
}
