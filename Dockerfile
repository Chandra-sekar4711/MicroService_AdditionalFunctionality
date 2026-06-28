FROM hashicorp/consul:latest

EXPOSE 8500 8600

CMD ["agent", "-server", "-ui", "-client=0.0.0.0", "-bootstrap-expect=1", "-data-dir=/consul/data"]s