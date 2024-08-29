FROM kalilinux/kali-rolling

RUN apt-get update && apt-get full-upgrade -y

RUN apt-get install -y kali-desktop-xfce xorg xrdp supervisor openvpn

RUN sed -i 's/port=3389/port=3390/g' /etc/xrdp/xrdp.ini

# Create TUN device
RUN mkdir -p /dev/net && \
    mknod /dev/net/tun c 10 200 && \
    chmod 600 /dev/net/tun

EXPOSE 3390

COPY xrdp.conf /etc/supervisor/conf.d/xrdp.conf
COPY ism34.ovpn /etc/openvpn/client/

CMD ["sleep", "infinity"]