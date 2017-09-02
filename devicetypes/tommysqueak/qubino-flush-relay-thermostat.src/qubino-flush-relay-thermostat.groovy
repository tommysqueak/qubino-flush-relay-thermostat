/**
 *  Qubino Flush 1 Relay Thermostat
 *
 *  https://github.com/tommysqueak/qubino-flush-relay-thermostat
 *
 *  Copyright 2016 Tom Philip
 *
 *  Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except
 *  in compliance with the License. You may obtain a copy of the License at:
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 *  on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License
 *  for the specific language governing permissions and limitations under the License.
 *
 */
metadata {
  definition (name: "Qubino Flush 1 Relay with Temperature Control v1", namespace: "tommysqueak", author: "Tom Philip") {
    capability "Actuator"
    capability "Sensor"
    capability "Switch"
    capability "Temperature Measurement"
    capability "Power Meter"
    capability "Energy Meter"
    capability "Refresh"
    capability "Configuration"

    capability "Thermostat"
    capability "Thermostat Mode"
    capability "Thermostat Heating Setpoint"
    capability "Thermostat Operating State"

    command "reset"
    command "temperatureUp"
    command "temperatureDown"
    command "toggleDesiredTemperature"

    attribute "combinedStateAndTemperature", "string"

    fingerprint inClusters: "0x5E, 0x86, 0x72, 0x5A, 0x73, 0x20, 0x27, 0x25, 0x26, 0x32, 0x85, 0x8E, 0x59, 0x70", outClusters: "0x20, 0x26", model: "0052", prod: "0002"
  }

  simulator {
    // TODO: define status and reply messages here
  }

  tiles(scale: 2) {
    multiAttributeTile(name:"switch", type:"thermostat", width:6, height:4, canChangeIcon: false) {
      tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
        attributeState("default", label:'${currentValue}°', unit:"°C", backgroundColors:[
          // Celsius Color Range
          [value: 0, color: "#153591"],
          [value: 7, color: "#1e9cbb"],
          [value: 15, color: "#90d2a7"],
          [value: 23, color: "#44b621"],
          [value: 29, color: "#f1d801"],
          [value: 33, color: "#d04e00"],
          [value: 36, color: "#bc2323"]])
      }

      tileAttribute("device.temperature", key: "VALUE_CONTROL") {
        attributeState("VALUE_UP", action: "temperatureUp")
        attributeState("VALUE_DOWN", action: "temperatureDown")
      }

      tileAttribute("device.switch", key: "SECONDARY_CONTROL") {
        attributeState "on", label:'${name}', icon: "st.switches.switch.on"
        attributeState "off", label:'${name}', icon: "st.switches.switch.off"
      }

      tileAttribute("device.thermostatOperatingState", key: "OPERATING_STATE") {
        attributeState("idle", label:'${name}', backgroundColor:"#80A2B7", icon: "st.thermostat.thermostat-down")
        attributeState("heating", label:'${name}', backgroundColor:"#ffa81e", icon: "st.thermostat.heating")
        attributeState("cooling", label:'${name}', backgroundColor:"#269bd2")
      }

      tileAttribute("device.thermostatMode", key: "THERMOSTAT_MODE") {
        attributeState("off", label:'${name}')
        attributeState("heat", label:'${name}')
        attributeState("cool", label:'${name}')
        attributeState("auto", label:'${name}')
      }

      tileAttribute("device.heatingSetpoint", key: "HEATING_SETPOINT") {
        attributeState("default", label:'${currentValue}', unit:"°C")
      }
    }

    standardTile("mode", "device.thermostatMode", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "off", action:"on", icon: "st.thermostat.heating-cooling-off"
      state "heat", action:"off", icon: "st.thermostat.auto", backgroundColor:"#A0DBA4"
    }

    standardTile("spacer", "spacerTile", decoration: "flat", width: 2, height: 2) {
    }

    standardTile("toggleDesiredSetpoint", "device.combinedStateAndTemperature", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "default", label:'-', icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff", defaultState: true

      state "off-4",  label:'4°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-5",  label:'5°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-6",  label:'6°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-7",  label:'7°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-8",  label:'8°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-9",  label:'9°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-10", label:'10°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-11", label:'11°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-12", label:'12°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-13", label:'13°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-14", label:'14°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-15", label:'15°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-16", label:'16°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-17", label:'17°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-18", label:'18°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-19", label:'19°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-20", label:'20°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-21", label:'21°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-22", label:'22°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-23", label:'23°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-24", label:'24°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-25", label:'25°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffe4bb"
      state "off-26", label:'26°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffd088"
      state "off-27", label:'26°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffd088"
      state "off-28", label:'28°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffd088"
      state "off-29", label:'29°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffd088"
      state "off-30", label:'30°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffd088"
      state "off-31", label:'31°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#eb9494"
      state "off-32", label:'32°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#eb9494"
      state "off-33", label:'33°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#eb9494"
      state "off-34", label:'34°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#eb9494"
      state "off-35", label:'35°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#eb9494"
      state "off-36", label:'36°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#eb9494"

      state "on-4",  label:'4°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "on-5",  label:'5°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "on-6",  label:'6°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "on-7",  label:'7°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "on-8",  label:'8°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "on-9",  label:'9°',  action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-10", label:'10°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-11", label:'11°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-12", label:'12°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-13", label:'13°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-14", label:'14°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-15", label:'15°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "on-16", label:'16°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-17", label:'17°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-18", label:'18°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-19", label:'19°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-20", label:'20°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-21", label:'21°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-22", label:'22°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-23", label:'23°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-24", label:'24°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-25", label:'25°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "on-26", label:'26°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "on-27", label:'26°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "on-28", label:'28°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "on-29", label:'29°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "on-30", label:'30°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "on-31", label:'31°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "on-32", label:'32°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "on-33", label:'33°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "on-34", label:'34°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "on-35", label:'35°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "on-36", label:'36°', action:"toggleDesiredTemperature", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
    }

    standardTile("toggleDesiredSetpointVisible", "device.heatingSetpoint", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state("default", label:'toggle', icon: "st.tesla.tesla-hvac", action:"toggleDesiredTemperature", unit:"°C", backgroundColors:[
        // Celsius Color Range
        [value: 0, color: "#153591"],
        [value: 7, color: "#1e9cbb"],
        [value: 15, color: "#90d2a7"],
        [value: 23, color: "#44b621"],
        [value: 29, color: "#f1d801"],
        [value: 33, color: "#d04e00"],
        [value: 36, color: "#bc2323"]])
    }

    valueTile("power", "device.power", decoration: "flat", width: 2, height: 1) {
      state "default", label:'${currentValue} W'
    }
    valueTile("energy", "device.energy", decoration: "flat", width: 2, height: 1) {
      state "default", label:'${currentValue} kWh'
    }
    standardTile("reset", "device.energy", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "default", label:'reset kWh', action:"reset", icon: "st.Seasonal Fall.seasonal-fall-009"
    }
    standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
    }

    main("toggleDesiredSetpoint")
    details("switch", "mode", "toggleDesiredSetpointVisible", "power", "energy","refresh", "spacer", "reset")
  }

  preferences {
    input "switchType", "enum", title: "Switch Type", options: ["Toggle", "Push Button"], required: true, displayDuringSetup: true, defaultValue: "Toggle"
    input "lastKnownState", "enum", title: "After Power Failure", options: ["Return to Last Known State", "Off"], required: true, displayDuringSetup: true
    input "autoTurnOff", "number", title: "Automatically Turn Off (minutes)", description: "Turn off, if left on after so many minutes", range: "0..542", displayDuringSetup: false
    input "temperatureOffset", "decimal", title: "Temperature Offset (°C)", description: "Adjust temperature by this many degrees °C", range: "-10..10", displayDuringSetup: false, defaultValue: 0
    input "temperatureReportOnChange", "decimal", title: "Temperature Reporting Change (°C)", description: "Reports temperature when the change is by this amount °C", displayDuringSetup: false, defaultValue: 0.5
  }
}

// parse events into attributes
def parse(String description) {
  def result = null
  def cmd = zwave.parse(description)

  if (cmd) {
    result = zwaveEvent(cmd)
    log.debug "Parsed ${cmd} to ${result.inspect()}"
  } else {
    log.debug "Non-parsed event: ${description}"
  }
  return result
}

//  Called when the device handler changes, or preferences change
def updated() {
  log.debug("updated")
  //  TODO: is this getting called for each updated field!?
  response(configure() + refresh())
}

//  NB I like the way to distingiush between, physical and digital events on the switch
//  The module will send BasicReports when it's physical. But when it's done by code, we ask for BinaryReports, so it comes in as digital.
def zwaveEvent_old(physicalgraph.zwave.commands.basicv1.BasicReport cmd)
{
  def events = []
  def thermostatMode = cmd.value ? "heat" : "off"
  def switchMode = cmd.value ? "on" : "off"

  //  Set it up to be on/off and either 'off' or 'heating' in terms of the mode.
  events << createEvent(name: "switch", value: switchMode, type: "physical")
  events << createEvent(name: "thermostatOperatingState", value: cmd.value ? "heating" : "idle", type: "physical")
  events << createEvent(name: "thermostatMode", value: thermostatMode, type: "physical")

  //  If we're turning 'off', then there's nothing to do, we're completely off in terms of switch and thermostat mode.
  //  If we're turning 'on'. Then we're in 'heating' mode and the controlTemperature() will decide whether it should remain on or turn-off
  //  immediately and let the temperature control the off/on of the switch.
  def onOffCommand = controlTemperature(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), thermostatMode, switchMode)

  if(onOffCommand) {
    [events + response(onOffCommand)]
  }
  else {
    events
  }
}

def zwaveEvent(physicalgraph.zwave.commands.basicv1.BasicReport cmd)
{
  raiseThermostatEvents(cmd.value)
}

def zwaveEvent(physicalgraph.zwave.commands.switchbinaryv1.SwitchBinaryReport cmd)
{
  raiseThermostatEvents(cmd.value)
}

def raiseThermostatEvents(switchValue)
{
  //  Store switch on/off. And also the thermostate mode, either heating (switch on) or idle (switch off)
  def events = []
  events << createEvent(name: "switch", value: switchValue ? "on" : "off", type: "digital")
  events << createEvent(name: "thermostatOperatingState", value: switchValue ? "heating" : "idle", type: "digital")
  events << createEvent(name: "combinedStateAndTemperature", value: (switchValue ? "on" : "off") + "-" + currentInt("temperature"), type: "digital")
  events
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv5.SensorMultilevelReport cmd)
{
  // 1 = temperature
  if(cmd.sensorType == 1){
    def temperatureEvent = createEvent(name: "temperature", value: cmd.scaledSensorValue, unit: cmd.scale == 1 ? "°F" : "°C")
    def combinedEvent = createEvent(name: "combinedStateAndTemperature", value: device.currentValue("switch") + "-" + cmd.scaledSensorValue)
    def onOffCommand = controlTemperature(cmd.scaledSensorValue, device.currentValue("heatingSetpoint"), device.currentValue("thermostatMode"))

    if(onOffCommand) {
      [temperatureEvent, combinedEvent, response(onOffCommand)]
    }
    else {
      temperatureEvent
    }
  }
  else {
    log.debug("WAT!")
  }
}

def zwaveEvent(physicalgraph.zwave.commands.meterv3.MeterReport cmd) {
  if (cmd.meterType == 1) {
    if (cmd.scale == 0) {
      return createEvent(name: "energy", value: cmd.scaledMeterValue, unit: "kWh", displayed: false)
    } else if (cmd.scale == 1) {
      return createEvent(name: "energy", value: cmd.scaledMeterValue, unit: "kVAh", displayed: false)
    } else if (cmd.scale == 2) {
      return createEvent(name: "power", value: Math.round(cmd.scaledMeterValue), unit: "W", displayed: false)
    } else {
      return createEvent(name: "electric", value: cmd.scaledMeterValue, unit: ["pulses", "V", "A", "R/Z", ""][cmd.scale - 3], displayed: false)
    }
  }
}

def zwaveEvent(physicalgraph.zwave.Command cmd) {
  // This will capture any commands not handled by other instances of zwaveEvent
  // and is recommended for development so you can see every command the device sends
  return createEvent(descriptionText: "Uncaptured event for ${device.displayName}: ${cmd}")
}

// ************
// * COMMANDS *
// ************

def toggleDesiredTemperature() {
  def currentSetpoint = currentDouble("heatingSetpoint")
  if (currentSetpoint > 15) {
    setHeatingSetpoint(8)
  }
  else {
    setHeatingSetpoint(22)
  }
}

def on() {
  log.debug("on")
  setThermostatMode("heat")
  controlTemperature(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), device.currentValue("thermostatMode"))
}

def off() {
  log.debug("off")
  setThermostatMode("off")
  turnOff()
}

def temperatureUp() {
  def nextTemp = currentDouble("heatingSetpoint") + 1
  //  TODO: have this as a safety value, set in config
  if (nextTemp > 30) {
      nextTemp = 30d
  }
  setHeatingSetpoint(nextTemp)
}

def temperatureDown() {
  def nextTemp = currentDouble("heatingSetpoint") - 1
  //  TODO: have this as a safety value, set in config
  if (nextTemp < 4) {
    nextTemp = 4d
  }
  setHeatingSetpoint(nextTemp)
}

def setHeatingSetpoint(desiredTemperature){
  log.debug "setting heatpoint $desiredTemperature"

  sendEvent(name: "thermostatMode", value: "heat")
  sendEvent(name: "heatingSetpoint", value: desiredTemperature, unit: "°C")

  controlTemperature(currentDouble("temperature"), desiredTemperature, "heat")
}

def controlTemperature(currentTemperature, desiredTemperature, thermostatMode){
  controlTemperature(currentTemperature, desiredTemperature, thermostatMode, device.currentValue("switch"))
}

def controlTemperature(currentTemperature, desiredTemperature, thermostatMode, switchMode){
  def hysteresisOn = -0.4
  def hysteresisOff = 0.4

  //  If we've set a temp, and we're turning the radiator on and off to keep the room at that temperature
  //  aka 'heat' mode
  if(thermostatMode == "heat") {
    if(desiredTemperature > (currentTemperature - hysteresisOn) && switchMode == "off") {
      turnOn()
    }
    else if(desiredTemperature < (currentTemperature - hysteresisOff) && switchMode == "on") {
      turnOff()
    }
  }
  else if(thermostatMode == "off") {
    if(switchMode == "on") {
      turnOff()
    }
  }
}

def turnOn() {
  zwave.basicV1.basicSet(value: 0xFF).format()
}

def turnOff() {
  zwave.basicV1.basicSet(value: 0x00).format()
}


//  ///////////////////
//  Thermostat Mode  //
//  ///////////////////
def heat() {
  on()
}

def emergencyHeat() {
  //  TODO: we should have a temperature like 4C, if it drops below that then we 'heat' but only to 4C
  setThermostatMode("emergency heat")
}

def cool() {
  //  Unsupported, so lets do nothing
}

def auto() {
  setThermostatMode("auto")
  //  TODO: auto means scheduled, so it's like we're 'off' between certain times and 'heat' between other times
}

def setThermostatMode(mode) {
  // thermostatMode - "emergency heat" "heat" "cool" "off" "auto"
  sendEvent(name: "thermostatMode", value: mode)
}

def setCoolingSetpoint(number){}

def fanOn() {}

def fanAuto() {}

def fanCirculate() {}

def setThermostatFanMode(string) {}

def refresh() {
  log.debug("refresh")

  delayBetween([
    zwave.switchBinaryV1.switchBinaryGet().format(),
    zwave.meterV2.meterGet(scale: 0).format(), // get kWh
    zwave.meterV2.meterGet(scale: 2).format(), // get Watts
    zwave.sensorMultilevelV5.sensorMultilevelGet(sensorType: 0x01).format(), //temperature
  ], 1000)
}

def reset() {
  log.debug("reset")

  delayBetween([
    zwave.meterV2.meterReset().format(),
    zwave.meterV2.meterGet(scale: 0).format(),
    zwave.meterV2.meterGet(scale: 2).format()
  ], 2000)
}

def configure() {
  log.debug("configure")

  def switchTypeConfig = switchType == "Push Button" ? 0 : 1
  def temperatureOffsetInCelsius = temperatureOffset ?: 0
  def temperatureReportOnChangeConfig = (temperatureReportOnChange ?: 0.5) * 10
  def autoTurnOffConfig = (autoTurnOff ?: 0) * 60

  if (temperatureReportOnChangeConfig > 127) {
    temperatureReportOnChangeConfig = 127
  }

  if (temperatureReportOnChangeConfig < 1) {
    temperatureReportOnChangeConfig = 1
  }

  def lastKnownStateConfig
  if(lastKnownState == "Off") {
    lastKnownStateConfig = 1
  }
  else {
    lastKnownStateConfig = 0
  }

  def temperatureOffsetConfig
  if (temperatureOffsetInCelsius > 0) {
    temperatureOffsetConfig = Math.round(temperatureOffsetInCelsius * 10)
  }
  else if(temperatureOffsetInCelsius < 0) {
    temperatureOffsetConfig = 1000 + Math.abs(Math.round(temperatureOffsetInCelsius * 10))
  }
  else {
    //  32536 = 0°C - default.
    temperatureOffsetConfig = 32536
  }

  delayBetween([
    //  Switch type: 0 - mono-stable (push button), 1 - bi-stable
    zwave.configurationV1.configurationSet(parameterNumber: 1, size: 1, scaledConfigurationValue: switchTypeConfig).format(),
    //  Turn off after a set time, in seconds. Useful for something staying on too long eg radiator. Max value - 32535 seconds
    zwave.configurationV1.configurationSet(parameterNumber: 11, size: 2, scaledConfigurationValue: autoTurnOffConfig).format(),
    //  State of switch after a power failure. 0 - back to last known state, 1 - off
    zwave.configurationV1.configurationSet(parameterNumber: 30, size: 1, scaledConfigurationValue: lastKnownStateConfig).format(),
    //  Temperature offset.
    //  32536 - default.
    //  1 to 100 - value from 0.1°C to 10.0°C is added to measured temperature.
    //  1001 to 1100 - value from -0.1 °C to -10.0 °C is subtracted from measured temperature.
    zwave.configurationV1.configurationSet(parameterNumber: 110, size: 2, scaledConfigurationValue: temperatureOffsetConfig).format(),
    //  When to report temperature.
    //  5 (0.5°C) - default
    //  0 - Reporting disabled
    //  1-127 = 0.1°C – 12.7°C, step is 0.1°C
    zwave.configurationV1.configurationSet(parameterNumber: 120, size: 1, scaledConfigurationValue: temperatureReportOnChangeConfig).format(),
  ], 3000)
}

//  temporary
def check() {
  log.debug("check")

  delayBetween([
    zwave.configurationV1.configurationGet(parameterNumber: 1).format(),
    zwave.configurationV1.configurationGet(parameterNumber: 11).format(),
    zwave.configurationV1.configurationGet(parameterNumber: 30).format(),
    zwave.configurationV1.configurationGet(parameterNumber: 110).format(),
    zwave.configurationV1.configurationGet(parameterNumber: 120).format(),
  ], 5000)
}

private currentInt(attributeName)
{
  if(device.currentValue(attributeName)) {
    return device.currentValue(attributeName).intValue()
  }
  else {
    return 0
  }
}

private currentDouble(attributeName)
{
  if(device.currentValue(attributeName)) {
    return device.currentValue(attributeName).doubleValue()
  }
  else {
    return 0d
  }
}
