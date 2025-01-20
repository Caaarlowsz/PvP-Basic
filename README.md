# PvP-Basic
> A basic soup pvp plugin with seven kits.

### Commands
| Command | Description | Permission |
| ------ | ------ | ------ |
| /pvp | Use to manage pvp plugin. | pvp.manage |
| /spawn | Use to go to spawn. | Without permission |
| /kit | Use to select a kit. | kit.<kit name> |

### Permissions
| Permission | Description |
| ------ | ------ |
| pvp.* | Grants all child permissions of the pvp plugin. |
| pvp.manage | Grants use of the /pvp command. |
| kit.* | Grants use of all kits. |
| kit.archer | Grants use of the Archer kit. |
| kit.enchanter | Grants use of the Enchanter kit. |
| kit.grandpa | Grants use of the Grandpa kit. |
| kit.healer | Grants use of the Healer kit. |
| kit.knight | Grants use of the Knight kit. |
| kit.tank | Grants use of the Tank kit. |
| kit.teleporter | Grants use of the Teleporter kit. |

[![N|Spigot](https://static.spigotmc.org/img/spigot.png)](https://www.spigotmc.org/)

Powered by Spigot.

## Installation
- This repository requires [Maven][mvn].
- This plugin uses [Spigot 1.8.8][buildtools].
```sh
git clone git@github.com:Caaarlowsz/PvP-Basic.git
mvn clean install
```

## License
**GPL-3.0**
*Free Software, Hell Yeah!*

[mvn]: <https://maven.apache.org/>
[buildtools]: <https://www.spigotmc.org/wiki/buildtools/>
