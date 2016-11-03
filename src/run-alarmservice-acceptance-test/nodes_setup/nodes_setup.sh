#!/usr/bin/env bash

. node.sh

app=`basename "$0"`

# Fine debug tool
export PS4='+(${BASH_SOURCE}:${LINENO}): ${FUNCNAME[0]:+${FUNCNAME[0]}(): }'
set -x

# Reads lines in nodes.txt. Each line contains three columns:
# 1. node host name (mapped by docker-compose)
# 2. node port
# 3. traffic card to be added to node using nodes REST api
#
# TODO: Provide path to nodes.txt as argument to add_boards()
#
function add_boards() {
    local host
    local port
    local board

    while read -r host port board || [[ -n "$host" ]]; do
        local session_id="$(login ${host} ${port})"
        printf '%s\n' "Login to ${host}:${port} returned session-id ${session_id}"

        local response_code=$(add_board ${host} ${port} ${session_id} ${board})
        printf '%s\n' "Add board ${board} to host ${host}:${port} returned response code: ${response_code}"

    done < nodes.txt
}

add_boards

exit 0

