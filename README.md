# **Simple Reports**

A lightweight, simple and easy-to-use report plugin with **Discord integration** for notifying admins about player reports in real-time.

### Features

* Instant Discord notifications via webhook
* Clean storage via YAML to get the last reports
* Lightweight and easy to integrate into any server

### Installation ![Release](https://img.shields.io/github/v/release/novee7/SimpleReports?style=for-the-badge)

1. Download the plugin JAR.
2. Place it in your server’s `plugins/` folder.
3. Start the server to generate the default configuration file (`config.yml`).

---

## CONFIG.YML

Open the `config.yml` file in the plugin folder. You will find:

```yaml
messages:
  usage: "&cUsage: /report <player> <type> <reason>"
  report:
    - "&7Thank you for reporting &#F4C4F3%player%&7. Your report has been submitted."
    - "&cRemember that reporting someone randomly may result in a ban."
  broadcast:
    - "&7[&c&lREP&r&7] &c%player% has been reported for %type%: &r%reason%"
  reload: "&aConfiguration reloaded successfully."

  last-report:
    server:
      - "&7The last report sent by a player is:"
      - "&7Target: &#F4C4F3%target%"
      - "&7Type: &#F4C4F3%type%"
      - "&7Reason: &#F4C4F3%reason%"
      - "&7Time: &#F4C4F3%time%"
    target:
      - "&7The last report sent by &#F4C4F3%player% &7is:"
      - "&7Target: &#F4C4F3%target%"
      - "&7Type: &#F4C4F3%type%"
      - "&7Reason: &#F4C4F3%reason%"
      - "&7Time: &#F4C4F3%time%"

settings:
  webhook: "https://discord.com/api/webhooks/your-webhook-url"

  storage:
    enabled: true
    file: "plugins/Reports/reports.yml"
    logs:
      created: "File reports.yml created."
      error-create: "Error while creating reports.yml: %error%"
      error-saving: "Error while saving reports.yml: %error%"

  tabcomplete:
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

## Changing the Discord Webhook

To send report notifications to a different Discord channel, simply update the `settings.webhook` URL in `config.yml` line 25.

```yaml
webhook: "https://discord.com/api/webhooks/your-webhook-url"
```

## Changing the storage configs

### Disable the storage or re-enable

To disabile the storage, simply update the `settings.storage.enabled` boolean in `config.yml` line 28. 

```yaml
    enabled: true
```

### File name & Path
To change the file path & name, simply update the `settings.storage.file` name in `config.yml` line 29. 
*To add or change directory just write the directory name and add a "/"*

```yaml
    file: "plugins/Reports/reports.yml"
```

Save the file and **reload or restart your server**. Your plugin will now send reports to the new webhook.

---

# Usage

* Players with the permission `reports.use` can submit reports using `/report <player> <type> <reason>` 
* Players with the permission `reports.staff` can get last player report or the last server report using:
* Server: `/lastreport` 
* Player: `/lastreport <player>` 
* Players with the permission `reports.admin` will receive report notifications directly in Discord and via minecraft

---

## License

**Proprietary – All rights reserved.**
See the `LICENSE.txt` file for details.

---

## THANK YOU ❤️‍🩹 

Please give a 🌟 to the repository if you're using the plugin or you just like it 

![Stars](https://img.shields.io/github/stars/novee7/SimpleReports?style=for-the-badge) 
