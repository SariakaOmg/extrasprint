# extrasprint

sudo update-alternatives --config java
sudo update-alternatives --config javac

chmod +x run.sh
./run.sh


# config apache2 :

Ajouter un port :

sudo nano /etc/apache2/ports.conf

Listen 78

Creer le virtual host du cdn :

sudo nano /etc/apache2/sites-available/cdn.conf

<VirtualHost *:78>
    ServerName cdn.localhost
    DocumentRoot /usr/local/cdn

    <Directory /usr/local/cdn>
        Options -Indexes
        Require all granted
    </Directory>
</VirtualHost>

Activer et redemarrer :

sudo a2ensite cdn
sudo apache2ctl configtest      # doit afficher "Syntax OK"
sudo systemctl restart apache2

sudo chmod -R o+rX /usr/local/cdn

curl -I http://cdn.localhost:78/patient/PAT0/qrcode.jpeg

# Avertissement AH00558 :
## Supprimer l'avertissement (facultatif) :
echo "ServerName localhost" | sudo tee /etc/apache2/conf-available/servername.conf
sudo a2enconf servername
sudo systemctl reload apache2

## Vérifier que le CDN fonctionne :
sudo systemctl status apache2 --no-pager | head -5
curl -I http://cdn.localhost:78/patient/PAT0/qrcode.jpeg