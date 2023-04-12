import {grpcCall} from "../client";
import {Logger} from "../platformApi";

class WebLogger implements Logger {
    constructor(private element: HTMLElement) {
    }
    log(message: string): void {
        var text = document.createElement("p")
        text.textContent = message
        this.element.appendChild(text);
    }
}

window.onload = (e) => {
    const paragraph = document.getElementById("log")!!
    grpcCall(new WebLogger(paragraph))
}