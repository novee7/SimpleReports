# REPORTS

A lightweight, simple and easy-to-use report plugin with **Discord integration** for notifying admins about player reports in real-time.

### Features

* Instant Discord notifications via webhook
* Lightweight and easy to integrate into any server

### Installation

1. Download the plugin JAR.
2. Place it in your server’s `plugins/` folder.
3. Start the server to generate the default configuration file (`config.yml`).

![Release](https://img.shields.io/github/v/release/novee7/SimpleReports?style=for-the-badge)

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

settings:
  webhook: "https://discord.com/api/webhooks/your-webhook-url"

exempts:
  player: "&cYou must specify a player to report."
  reason: "&cYou must specify a reason to report."
  type: "&cInvalid report type. Valid types are: cheating, exploiting, harassment, other."
```

### Some strings are in lists so that multiple messages can be added or removed if necessary or desired without forking the plugin.

*All the configs support hex codes with the prefix & and basic Minecraft colour codes.*
*Placeholders ONLY work if used by the plugin's default config.yml.*

## Changing the Discord Webhook

To send report notifications to a different Discord channel, simply update the `webhook` URL in `config.yml` line 11.

```yaml
webhook: "https://discord.com/api/webhooks/your-webhook-url"
```

Save the file and **reload or restart your server**. Your plugin will now send reports to the new webhook.

---

# Usage

* Players with the permission `reports.use` can submit reports using `/report <player> <type> <reason>` 
* Players with the permission `reports.admin` will receive report notifications directly in Discord and via minecraft

---

## License

**Proprietary – All rights reserved.**
See the `LICENSE.txt` file for details.

---

## THANK YOU ❤️‍🩹 

Please give a 🌟 to the repository if you're using the plugin or you just like it 

![Stars](https://img.shields.io/github/stars/novee7/SimpleReports?style=for-the-badge) 
