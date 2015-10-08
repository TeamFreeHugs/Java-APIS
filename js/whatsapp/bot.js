$(document).ready(function () {

    var killTypes = ['$1 was murdered',
        'Voldermort (aka Shadow Wizard) used [Avada Kedavra](http://harrypotter.wikia.com/wiki/Killing_Curse)' +
        ' used on $1', '$1 disappeared for no reason',
        '$1 played too much Minecraft and got eaten by a zombie", "$1 sleeps with the fishes',
        '$1 has been entered into a Death Note", "$1 was accidentally decapitated in an old factory',
        'A noose appeared around $1\'s neck and he tripped and fell off a cliff', 'An axe fell on $1\'s head.',
        "in\u0252z\u0258m\u0252\u042f.A.M poured trifluoromethanesulfonic acid on $1"];


    var handlers = {};
    whatsapp.attachHandler({
        newMessage: function (text) {
            if (text.startsWith('..')) {
                var command = text.substr(2).split(' ')[0];
                var args = text.substr(3 + command.length);
                var handler = handlers[command];
                if (typeof handler === 'function') {
                    handler(args);
                } else {
                    whatsapp.sendMessage('Command ' + command + ' is unknown. ');
                }
            }
        }
    });

    handlers.echo = function (args) {
        if (args === '')
            whatsapp.sendMessage('Nothing to echo!');
        else
            whatsapp.sendMessage(args);
    };

    handlers.kill = function (args) {
        whatsapp.sendMessage(killTypes[Math.floor(Math.random() * (killTypes.length + 1))].replace(/\$1/g, args));
    };

    handlers.randRange = function (args) {
        var parts = args.split(' ');
        if (parts.length < 2) {
            whatsapp.sendMessage(Math.random());
            return;
        }
        console.log(args);
        var max = parseInt(parts[1]),
            min = parseInt(parts[0]);
        whatsapp.sendMessage(Math.floor(Math.random() * (max - min + 1)) + min);
    };

    handlers.help = function () {
        whatsapp.sendMessage('Hi. I\'m Uni*\'s chatbot, ported to whatsapp, writted in JS.');
    };

    handlers.eval = function (args) {
        if (args.trim() === '') {
            whatsapp.sendMessage('Syntax: ..eval (thing_to_eval)');
            return;
        }
        if (args.match(/\w+\.prototype.+/) != null) {
            whatsapp.sendMessage('Looks like you\'re trying to access the prototype of some object! This is disabled for security reasons.');
            return;
        }

        var mask = {};
        for (var p in this)
            mask[p] = undefined;
        mask.JSON = JSON;
        try {
            whatsapp.sendMessage('"' + new Function('with(this) { return ' + args + '}').call(mask) + '"');
        } catch (e) {
            if (e instanceof ChatError) {
                whatsapp.sendMessage('<Empty response for ' + args + '>');
            } else
                whatsapp.sendMessage(e.name + ': ' + e.message)
        }
    };


    if (!whatsapp.isWatching)
        whatsapp.startWatching();
})
;