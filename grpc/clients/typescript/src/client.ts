import {GreeterClient} from "./generated/proto/src/main/proto/ServiceServiceClientPb";
import {HelloRequest, SpellRequest} from "./generated/proto/src/main/proto/service_pb";
import {Logger} from "./platformApi";

export async function grpcCall(logger: Logger) {
    const port = 8088
    const greeter = new GreeterClient('http://localhost:' + port, null, null);

    const name = 'typescript'

    const helloRequest = new HelloRequest();
    helloRequest.setName(name);

    const helloResponse = await greeter.helloCall(helloRequest, null);
    logger.log(helloResponse.getMessage())

    const spellRequest = new SpellRequest();
    spellRequest.setText('typescript');

    await new Promise((resolve) => {
        const spellResponse = greeter.spell(spellRequest);
        spellResponse.on('data', (reply)=>{
            logger.log(reply.getMessage())
        })
        spellResponse.on('end', ()=>{
            logger.log('Done!')
            resolve(undefined)
        })
    })
}