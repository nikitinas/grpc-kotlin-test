import * as xhr from 'xhr2'

export function addXMLHttpRequest() {
    global.XMLHttpRequest = xhr
}