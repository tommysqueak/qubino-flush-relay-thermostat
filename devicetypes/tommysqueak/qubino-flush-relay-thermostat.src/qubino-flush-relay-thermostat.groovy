/**
 *  Qubino Flush 1 Relay Thermostat
 *
 *  https://github.com/tommysqueak/qubino-flush-relay-thermostat
 *
 *  Copyright 2020 Tom Philip
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
 *  Revision History
 *  ==============================================
 *  2018-09-23 - Added when the costs/kwH were reset and stored the previous costs.
 *
 */
metadata {
  definition (name: "Qubino Flush 1 Relay with Temperature Control v11", namespace: "tommysqueak", author: "Tom Philip",  ocfDeviceType: "oic.d.thermostat", vid: "778fbf4b-38e8-3d77-a902-aef87e285c12", mnmn: "SmartThingsCommunity") {
    capability "Thermostat"
    capability "Thermostat Mode"
    capability "Thermostat Heating Setpoint"
    capability "Thermostat Operating State"

    capability "Switch"
    capability "Temperature Measurement"
    capability "Power Meter"
    capability "Energy Meter"
    capability "Refresh"
    capability "Configuration"

    command "reset"
    command "temperatureUp"
    command "temperatureDown"
    command "toggleDesiredTemperature"

    attribute "combinedStateAndTemperature", "string"
    attribute "costPerHour", "number"
    attribute "costToDate", "number"
    attribute "previousCostToDate", "number"
    attribute "costResetAt", "string"
    attribute "previousCostToDateRange", "string"

    fingerprint inClusters: "0x5E, 0x86, 0x72, 0x5A, 0x73, 0x20, 0x27, 0x25, 0x26, 0x32, 0x85, 0x8E, 0x59, 0x70", outClusters: "0x20, 0x26", model: "0052", prod: "0002"
  }

  simulator {
  }

  tiles(scale: 2) {
    multiAttributeTile(name:"switch", type:"thermostat", width:6, height:4, canChangeIcon: false) {
      tileAttribute("device.temperature", key: "PRIMARY_CONTROL") {
        attributeState("default", label:'${currentValue}°', unit:"C", backgroundColors:[
          // Celsius Color Range
          [value: 0, color: "#153591"],
          [value: 7, color: "#1e9cbb"],
          [value: 15, color: "#90d2a7"],
          [value: 23, color: "#44b621"],
          [value: 29, color: "#f1d801"],
          [value: 33, color: "#d04e00"],
          [value: 36, color: "#bc2323"]])
      }

      tileAttribute("device.heatingSetpoint", key: "VALUE_CONTROL") {
        attributeState("VALUE_UP", action: "temperatureUp", icon: "st.Weather.weather2")
        attributeState("VALUE_DOWN", action: "temperatureDown", icon: "st.Weather.weather2")
      }

      tileAttribute("device.switch", key: "SECONDARY_CONTROL") {
        attributeState "on", label: "on", icon: "st.switches.switch.on"
        attributeState "off", label: "off", icon: "st.switches.switch.off"
      }

      tileAttribute("device.thermostatOperatingState", key: "OPERATING_STATE") {
        attributeState("default", label: "-", icon: "st.tesla.tesla-hvac", defaultState: true)
        attributeState("idle", label: "idle", backgroundColor:"#cccccc")
        attributeState("heating", label: "heating", backgroundColor:"#e86d13")
        attributeState("cooling", label: "cooling", backgroundColor:"#00a0dc")
      }

      tileAttribute("device.thermostatMode", key: "THERMOSTAT_MODE") {
        attributeState("off", label: "off", icon: "st.thermostat.heating-cooling-off")
        attributeState("heat", label: "heat", icon: "st.thermostat.heat")
        attributeState("cool", label: "cool")
        attributeState("auto", label: "auto")
      }

      tileAttribute("device.heatingSetpoint", key: "HEATING_SETPOINT") {
        attributeState("default", label:'${currentValue}', unit:"C")
      }
    }

    standardTile("mode", "device.thermostatMode", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "off", action: "heat", icon: "st.thermostat.heating-cooling-off"
      state "heat", action: "off", icon: "st.thermostat.heat"
    }

    standardTile("toggleDesiredSetpoint", "device.combinedStateAndTemperature", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "default", label:'-', icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff", defaultState: true

      state "off-4",  label:'4°',  action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-5",  label:'5°',  action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-6",  label:'6°',  action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-7",  label:'7°',  action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-8",  label:'8°',  action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-9",  label:'9°',  action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-10", label:'10°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-11", label:'11°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-12", label:'12°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-13", label:'13°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-14", label:'14°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-15", label:'15°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-16", label:'16°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-17", label:'17°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-18", label:'18°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-19", label:'19°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-20", label:'20°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-21", label:'21°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-22", label:'22°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-23", label:'23°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-24", label:'24°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-25", label:'25°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-26", label:'26°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-27", label:'26°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-28", label:'28°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-29", label:'29°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-30", label:'30°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-31", label:'31°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-32", label:'32°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-33", label:'33°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-34", label:'34°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-35", label:'35°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-36", label:'36°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-37", label:'37°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-38", label:'38°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-39", label:'39°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"
      state "off-40", label:'40°', action:"heat", icon: "st.thermostat.heating-cooling-off", backgroundColor: "#ffffff"

      state "idle-4",  label:'4°',  action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-5",  label:'5°',  action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-6",  label:'6°',  action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-7",  label:'7°',  action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-8",  label:'8°',  action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-9",  label:'9°',  action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-10", label:'10°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-11", label:'11°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-12", label:'12°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-13", label:'13°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-14", label:'14°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-15", label:'15°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-16", label:'16°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-17", label:'17°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-18", label:'18°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-19", label:'19°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-20", label:'20°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-21", label:'21°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-22", label:'22°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-23", label:'23°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-24", label:'24°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-25", label:'25°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-26", label:'26°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-27", label:'26°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-28", label:'28°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-29", label:'29°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-30", label:'30°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-31", label:'31°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-32", label:'32°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-33", label:'33°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-34", label:'34°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-35", label:'35°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"
      state "idle-36", label:'36°', action:"off", icon: "https://raw.githubusercontent.com/tommysqueak/qubino-flush-relay-thermostat/master/devicetypes/tommysqueak/qubino-flush-relay-thermostat.src/heating-cooling-idle@2x.png", backgroundColor: "#00a0dc"

      state "heating-4",  label:'4°',  action:"off", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "heating-5",  label:'5°',  action:"off", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "heating-6",  label:'6°',  action:"off", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "heating-7",  label:'7°',  action:"off", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "heating-8",  label:'8°',  action:"off", icon: "st.thermostat.heating", backgroundColor: "#153591"
      state "heating-9",  label:'9°',  action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-10", label:'10°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-11", label:'11°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-12", label:'12°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-13", label:'13°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-14", label:'14°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-15", label:'15°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#1e9cbb"
      state "heating-16", label:'16°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-17", label:'17°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-18", label:'18°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-19", label:'19°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-20", label:'20°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-21", label:'21°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-22", label:'22°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-23", label:'23°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-24", label:'24°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-25", label:'25°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#ffa81e"
      state "heating-26", label:'26°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "heating-27", label:'26°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "heating-28", label:'28°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "heating-29", label:'29°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "heating-30", label:'30°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#d04e00"
      state "heating-31", label:'31°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "heating-32", label:'32°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "heating-33", label:'33°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "heating-34", label:'34°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "heating-35", label:'35°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
      state "heating-36", label:'36°', action:"off", icon: "st.thermostat.heating", backgroundColor: "#bc2323"
    }

    valueTile("power", "device.power", decoration: "flat", width: 2, height: 1) {
      state "default", label:'${currentValue} W'
    }
    valueTile("costPerHour", "device.costPerHour", decoration: "flat", width: 2, height: 1) {
      state "default", label:'€ ${currentValue}'
    }
    valueTile("energy", "device.energy", decoration: "flat", width: 2, height: 1) {
      state "default", label:'${currentValue} kWh'
    }
    valueTile("costToDate", "device.costToDate", decoration: "flat", width: 2, height: 1) {
      state "default", label:'€${currentValue}'
    }
    valueTile("previousCostToDateRange", "device.previousCostToDateRange", decoration: "flat", width: 2, height: 1) {
      state "default", label:'${currentValue}'
    }
    valueTile("previousCostToDate", "device.previousCostToDate", decoration: "flat", width: 2, height: 1) {
      state "default", label:'€${currentValue}'
    }
    standardTile("reset", "device.energy", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "default", label:'reset kWh', action:"reset", icon: "st.Seasonal Fall.seasonal-fall-009"
    }
    valueTile("costResetAt", "device.costResetAt", decoration: "flat", inactiveLabel: false, width: 2, height: 1) {
      state "costResetAt", label:'Since:\n ${currentValue}'
    }
    standardTile("refresh", "device.power", inactiveLabel: false, decoration: "flat", width: 2, height: 2) {
      state "default", label:'', action:"refresh.refresh", icon:"st.secondary.refresh"
    }

    main("toggleDesiredSetpoint")
    details("switch", "mode", "power", "costPerHour", "energy", "costToDate", "refresh", "reset", "previousCostToDateRange", "previousCostToDate")
  }

  preferences {
    input "switchType", "enum", title: "Switch Type", options: ["Toggle", "Push Button"], required: true, displayDuringSetup: true, defaultValue: "Toggle"
    input "lastKnownState", "enum", title: "After Power Failure", options: ["Return to Last Known State", "Off"], required: true, displayDuringSetup: true
    input "autoTurnOff", "number", title: "Automatically Turn Off (minutes)", description: "Turn off, if left on after so many minutes", range: "0..542", displayDuringSetup: false
    input "temperatureOffset", "decimal", title: "Temperature Offset (°C)", description: "Adjust temperature by this many degrees °C", range: "-10..10", displayDuringSetup: false, defaultValue: 0
    input "temperatureReportOnChange", "decimal", title: "Temperature Reporting Change (°C)", description: "Reports temperature when the change is by this amount °C", displayDuringSetup: false, defaultValue: 0.5
    input "costPerKwh", type: "decimal", title: "Cost per kWh", required: false, displayDuringSetup: false
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
  //  Store the thermostat mode, either heating (switch on) or idle (switch off)
  def operatingState = switchValue ? "heating" : "idle"
  def events = []
  events << createEvent(name: "thermostatOperatingState", value: operatingState)
  events << createCombinedStateEvent(device.currentValue("thermostatMode"), operatingState, currentInt("temperature"))
  events
}

def zwaveEvent(physicalgraph.zwave.commands.sensormultilevelv5.SensorMultilevelReport cmd)
{
  // 1 = temperature
  if(cmd.sensorType == 1){
    def temperatureEvent = createEvent(name: "temperature", value: cmd.scaledSensorValue, unit: cmd.scale == 1 ? "F" : "C")
    def combinedEvent = createCombinedStateEvent(device.currentValue("thermostatMode"), device.currentValue("thermostatOperatingState"), cmd.scaledSensorValue.intValue())
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
      def powerEvent = createEvent(name: "energy", value: cmd.scaledMeterValue, unit: "kWh", displayed: false)
      def costEvent

      if (costPerKwh) {
        double costAsDecimal = costPerKwh as double
        costEvent = createEvent(name: "costToDate", value: String.format("%5.2f", cmd.scaledMeterValue * costAsDecimal), unit: "€", displayed: false)
      }
      else {
        costEvent = createEvent(name: "costToDate", value: "-", unit: "€", displayed: false)
      }

      return [powerEvent, costEvent]
    } else if (cmd.scale == 1) {
      return createEvent(name: "energy", value: cmd.scaledMeterValue, unit: "kVAh", displayed: false)
    } else if (cmd.scale == 2) {
      def powerEvent = createEvent(name: "power", value: Math.round(cmd.scaledMeterValue), unit: "W", displayed: false)
      def costEvent

      if (costPerKwh) {
        double costAsDecimal = costPerKwh as double
        costEvent = createEvent(name: "costPerHour", value: String.format("%5.2f", (cmd.scaledMeterValue / 1000) * costAsDecimal), unit: "€", displayed: false)
      }
      else {
        costEvent = createEvent(name: "costPerHour", value: "-", unit: "€", displayed: false)
      }

      return [powerEvent, costEvent]
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

def temperatureUp() {
  def nextTemp = currentDouble("heatingSetpoint") + 0.5
  //  TODO: have this as a safety value, set in config
  if (nextTemp > 30) {
      nextTemp = 30d
  }
  setHeatingSetpoint(nextTemp)
}

def temperatureDown() {
  def nextTemp = currentDouble("heatingSetpoint") - 0.5
  //  TODO: have this as a safety value, set in config
  if (nextTemp < 4) {
    nextTemp = 4d
  }
  setHeatingSetpoint(nextTemp)
}

def setHeatingSetpoint(desiredTemperature){
  log.debug "setting heatpoint $desiredTemperature"
  sendEvent(name: "heatingSetpoint", value: desiredTemperature, unit: getTemperatureScale())
  controlTemperature(currentDouble("temperature"), desiredTemperature, device.currentValue("thermostatMode"))
}

def controlTemperature(currentTemperature, desiredTemperature, thermostatMode){
  controlTemperature(currentTemperature, desiredTemperature, thermostatMode, device.currentValue("thermostatOperatingState"))
}

def controlTemperature(currentTemperature, desiredTemperature, thermostatMode, operatingState){
  def hysteresisOn = -0.4
  def hysteresisOff = 0.4

  //  If we've set a temp, and we're turning the radiator on and off to keep the room at that temperature
  //  aka 'heat' mode
  if(thermostatMode == "heat") {
    if(desiredTemperature > (currentTemperature - hysteresisOn) && operatingState == "idle") {
      turnRadiatorOn()
    }
    else if(desiredTemperature < (currentTemperature - hysteresisOff) && operatingState == "heating") {
      turnRadiatorOff()
    }
  }
  else if(thermostatMode == "off") {
    if(operatingState == "heating") {
      turnRadiatorOff()
    }
  }
}

def turnRadiatorOn() {
  zwave.basicV1.basicSet(value: 0xFF).format()
}

def turnRadiatorOff() {
  zwave.basicV1.basicSet(value: 0x00).format()
}


//  ///////////////////
//  Switch           //
//  ///////////////////
def on() {
  log.debug("on")
  heat()
}

def off() {
  log.debug("off")
  setThermostatMode("off")
}

//  ///////////////////
//  Thermostat Mode  //
//  ///////////////////
def heat() {
  setThermostatMode("heat")
}

def emergencyHeat() {
  heat()
}

def cool() {
  //  Unsupported, so lets do nothing
}

def auto() {
  //  Unsupported. auto means scheduled, so it's like we're 'off' between certain times and 'heat' between other times
}

def setThermostatMode(mode) {
  log.debug "mode: $mode"
  // thermostatMode - "emergency heat" "heat" "auto" "eco" "cool" "off" "rush hour"
  def supportedMode = ["emergency heat", "heat", "auto", "eco"].contains(mode) ? "heat" : "off"

  sendEvent(name: "thermostatMode", value: supportedMode)
  sendEvent(name: "switch", value: supportedMode == "heat" ? "on" : "off", displayed: false)
  sendEvent(createCombinedStateEvent(mode, device.currentValue("thermostatOperatingState"), currentInt("temperature")))

  switch(supportedMode) {
    case "heat":
      controlTemperature(device.currentValue("temperature"), device.currentValue("heatingSetpoint"), "heat")
      break; 
    case "off": 
      turnRadiatorOff()
      break; 
  }
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

  //  Temp. date corrections etc
  //Calendar rightNow = Calendar.getInstance();
  //rightNow.add(Calendar.YEAR, -1)
  //def backThen = rightNow.getTime().format("d MMM yyyy",location.timeZone)
  //sendEvent(name: "costResetAt", value: backThen, displayed: false)

  //def now = new Date().format("d MMM yyyy",location.timeZone)
  //sendEvent(name: "previousCostToDateRange", value: backThen + "-\n" + now + " ", displayed: false)
}

def reset() {
  log.debug("reset")

  delayBetween([
    zwave.meterV2.meterReset().format(),
    zwave.meterV2.meterGet(scale: 0).format(),
    zwave.meterV2.meterGet(scale: 2).format()
  ], 2000)


  def now = new Date().format("d MMM yyyy",location.timeZone)
  sendEvent(name: "previousCostToDateRange", value: device.currentValue("costResetAt") + "-\n" + now, displayed: false)
  sendEvent(name: "costResetAt", value: now, displayed: true)
  sendEvent(name: "previousCostToDate", value: currentDouble("costToDate"), unit: "€", displayed: true)

  sendEvent(name: "energy", value: 0, unit: "kWh", displayed: true)
  sendEvent(name: "costToDate", value: 0, unit: "€", displayed: true)
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

  sendEvent(name: "supportedThermostatModes", value: ["heat", "off"], displayed: false)
  sendEvent(name: "supportedThermostatFanModes", value: ["auto"], displayed: false)

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

private createCombinedStateEvent(mode, operatingState, temperature)
{
  //  TODO: can we use a label with the state, then maybe we can
  //  use the state to choose button color/look but the label to show the temperature
  if (mode == "off") {
    createEvent(name: "combinedStateAndTemperature", value: "off-" + temperature, displayed: false)
  }
  else {
    createEvent(name: "combinedStateAndTemperature", value: operatingState + "-" + temperature, displayed: false)
  }
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
