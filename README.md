# CombatDice
Working to do CombatDice, a combat of dice from turns.

# To start with them
First, you need launch server, then you need build the docker file

```Docker build . -t xhavcked/spigot.1.16.5:v1```

Then, once create the image you need create the container.

```docker run --name Spigot -v spigot -ti -p 25565:25565 xhavcked/spigot.1.16.5:v1```

Ok, lets go, we can start to build the plugin.

"Remember" you need change the ip of the server if u want do public. You have a variable that name is it MINEIP, you can change the ip when you start the docker un command ex:

```docker run --name Spigot -v spigot -ti -p 25565:25565 -e MINEIP='YOUR-IP-HERE' xhavcked/spigot.1.16.5:v1```
