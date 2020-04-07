'use strict';

    window.onload = init;
function init() {
	if (window.Event) {
	document.captureEvents(Event.MOUSEMOVE);
	}
	document.onmousemove = getCursorXY;
}
var canvas = document.getElementById("canvas");
var ctx = document.getElementById("canvas").getContext("2d");

    function drawLine(x_first, y_first, x_second, y_second){
        ctx.beginPath();
        ctx.moveTo( x_first, y_first );
        ctx.lineTo( x_second, y_second );
        ctx.lineWidth = 2;
        ctx.stroke();
    }


let ee;










class Entity{
i = 0;
q = 0;
constructor(name, x, y) {
    this.sx = x;
    this.sy = y;
    this.name = name;
    this.i=x;
    this.q=y;
}
}

for (var i = 0; i < 3; i++) {
let something = new Entity('some'+i,i*100, i*100);
setInterval(function(){
setMovable(something,i*100,i*100);
//setMovable('some2',500,500);
}, 10);
}







function setMovable(some, isi,isq){
let si = isi;
let sq = isq;
setMovable2(some,si,sq);
}


function setMovable2(some,si,sq){
if(ee){
let thisSi = si;
let thisSq = sq;
some.i = step(some.i,ee.pageX);
some.q = step(some.q,ee.pageY);
some.i = stepLim(some.i,some.sx);
some.q = stepLim(some.q,some.sy);
move(some.name,some.i,some.q);
//move('some2',q,i);
ctx.clearRect(0, 0, canvas.width, canvas.height);
//drawLine(i,q,ee.pageX,ee.pageY);
//drawLine(q,i,ee.pageX,ee.pageY);
//drawLine(q,i,i,q);
}
}

function getCursorXY(e) {
    ee = e;
    document.getElementById('cursorX').value = e.pageX;
	document.getElementById('cursorX').style.left = e.pageX + "px";
	document.getElementById('cursorY').value = e.pageY;
	document.getElementById('cursorY').style.top = e.pageY + "px";
    //document.getElementById('cursorX').value = i;
    //document.getElementById('cursorY').value = q;
}

let speed = 0.01;
function step(from,to){
from = from-(from-to)*speed;
return from;
}

function stepLim(n,s){
//speed = Math.abs((s/(s-n))/1000)
//n = n-(s/(s-n));
//n = s+(s/(s-n));
return n;
}

function move(element, x, y){
if(document.getElementById(element)){
element = document.getElementById(element);
element.style.position = "absolute";
element.style.top = Math.round(y)+"px";
element.style.left = Math.round(x)+"px";
element.innerHTML = Math.round(x) + "<br/>" +Math.round(y);
}
}
