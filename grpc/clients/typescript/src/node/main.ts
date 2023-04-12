import * as Client from './../client'
import { addXMLHttpRequest } from './fix/fixNode'

addXMLHttpRequest()

Client.grpcCall()