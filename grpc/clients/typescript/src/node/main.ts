import * as Client from './../client'
import { addXMLHttpRequest } from './fix/fixNode'
import {Logger} from "../platformApi";

addXMLHttpRequest()

class NodeLogger implements Logger {
    log(message: string): void {
        console.log(message)
    }
}
Client.grpcCall(new NodeLogger())