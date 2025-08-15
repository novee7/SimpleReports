# **Simple Reports**

A lightweight, simple and easy-to-use report plugin with **Discord integration** for notifying admins about player reports in real-time.

### Features

* Instant Discord notifications via webhook
* Clean storage via YAML or SQLite
* Lightweight and easy to integrate into any server

### Installation 

1. Download the plugin JAR.
2. Place it in your server’s `plugins/` folder.
3. Start the server to generate the default configuration file (`config.yml`).

![Release](https://img.shields.io/github/v/release/novee7/SimpleReports?style=for-the-badge)

---

## Editing the configuration

Open the `config.yml` file in the plugin folder. You will find:

```yaml
#                     ┬─┐┌─┐┌─┐┌─┐┬─┐┌┬┐┌─┐
#                     ├┬┘├┤ ├─┘│ │├┬┘ │ └─┐
#                     ┴└─└─┘┴  └─┘┴└─ ┴ └─┘

#               The messages are in Lists so you can
#             add or remove as many messages as you want.
#             All the configuration supports HEX colors.
#               Placeholders ONLY work if used by the 
#                   plugin's default config.yml.
#

messages:
  usage: "&cUsage: /report <player> <type> <reason>"

  report:
    - "&7Thank you for reporting &c%player%&7. Your report has been submitted."
    - "&cRemember that reporting someone randomly may result in a ban."

  broadcast:
    - "&7[&c&lREP&r&7] &c%player% has been reported for %type%: &r%reason%"

  reload: "&aConfiguration reloaded successfully."

  last-report:

    # Last report made on the server
    server:
      - "&7[&c&lREP&r&7]  The last report sent by a player is:"
      - "&7Target: &c%target%"
      - "&7Type: &c%type%"
      - "&7Reason: &c%reason%"
      - "&7Time: &c%time%"

    # Last report made by a specific player (%player%)
    target:
      - "&7[&c&lREP&r&7] The last report sent by &c%player% &7is:"
      - "&7Target: &c%target%"
      - "&7Type: &c%type%"
      - "&7Reason: &c%reason%"
      - "&7Time: &c%time%"

settings:

  webhook:
    # Enable (true) or disable (false) the Discord integration.
    enabled: true
    # Replace "your-webhook-url" with your actual Discord webhook URL.
    url: "https://discord.com/api/webhooks/your-webhook-url"

  storage:
    # Enable (true) or disable (false) the storage system.
    enabled: true

    # Storage types: yaml, sqlite
    type: "sqlite"

    # Paths/names of the storage files.
    file:
      yaml: "plugins/Reports/reports.yml"
      sqlite: "plugins/Reports/reports.db"

    logs:
      created: "Storage created."
      error-create: "Error while creating storage: %error%"
      error-saving: "Error while saving storage: %error%"

  tabcomplete:
    # Enable (true) or disable (false) the tab completion feature.
    enabled: true

    cheating:
      - "Macros"
      - "CW"
      - "AutoTotem"
      - "TriggerBot"
      - "AimAssist"
      - "Reach"
    exploiting:
      - "Command abuse"
      - "Glitching"
    harassment: []
    other: []


exempts:
  argument: "&cArgument not found."
  no-reports: "&cNo reports found."
  player: "&cYou must specify a player to report."
  reason: "&cYou must specify a reason to report."
  type: "&cInvalid report type. Valid types are: cheating, exploiting, harassment, other."
```

### Some strings are in lists so that multiple messages can be added or removed if necessary or desired without forking the plugin.

*All the configs support hex codes with the prefix & and basic Minecraft colour codes.*
*Placeholders ONLY work if used by the plugin's default config.yml.*

## Changing the Discord configs

### Disable the Discord webhook or re-enable

To disabile the storage, simply update the `settings.webhook.enabled` boolean in `config.yml` line 46. 


```yaml
    # Enable (true) or disable (false) the Discord integration.
    enabled: true
```

### Changing the webhoo URL

To send report notifications to a different Discord channel, simply update the `settings.webhook.url` URL in `config.yml` line 48.

```yaml
    # Replace "your-webhook-url" with your actual Discord webhook URL.
    url: "https://discord.com/api/webhooks/your-webhook-url"
```

## Changing the storage configs

### Disable the storage or re-enable

To disabile the storage, simply update the `settings.storage.enabled` boolean in `config.yml` line 52. 

```yaml
    # Enable (true) or disable (false) the storage system.
    enabled: true
```

### Changing storage type
To change the storage type go to `settings.storage.type` name in `config.yml` line 55. 


```yaml
    # Storage types: yaml, sqlite
    type: "sqlite"
```

### File name & Path
To change the file path & name, simply update the `settings.storage.file` name in `config.yml` line 58. 
*To add or change directory just write the directory name and add a "/"*

```yaml
    # Paths/names of the storage files.
    file:
      yaml: "plugins/Reports/reports.yml"
      sqlite: "plugins/Reports/reports.db"
```

Save the file and **reload or restart your server**. Your plugin will now send reports to the new webhook.

---

# Usage

Players with the permission `reports.use` can submit reports using `/report <player> <type> <reason>` 

Players with the permission `reports.staff` can get last player report or the last server report using:
* Server: `/lastreport` 
* Player: `/lastreport <player>`

Players with the permission `reports.admin` will receive report notifications directly in Discord and via minecraft

---

## License

**Proprietary – All rights reserved.**
See the `LICENSE.txt` file for details.

---

## THANK YOU ❤️‍🩹 

Please give a 🌟 to the repository if you're using the plugin or you just like it 

![Stars](https://img.shields.io/github/stars/novee7/SimpleReports?style=for-the-badge) 
