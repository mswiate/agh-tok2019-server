const CORE_SYMBOLS = {
    BAMBOOS   : [0, 1, 6],
    CHARACTERS: [0, 3, 6],
    CIRCLES   : [0, 5, 6],
    // WINDS     : [0, 11, 6],
    // DRAGONS   : [0, 13, 6],
};
const EXTENDED_SYMBOLS = {  
    SEASONS   : [0, 7, 2],
    FLOWERS   : [0, 9, 4],   
};

function TileDeck() {
    const self = this;  
    this.tile_list = [];

    this.init = function(extended=false) {
        // Clear existing deck
        self.tile_list.length = 0;

        // Add core tiles
        var keys = Object.keys(CORE_SYMBOLS)
        var symbols = {a: CORE_SYMBOLS[keys[ keys.length * Math.random() << 0]]};
        self._addSymbolCollection(symbols, 4);

        // If extended, also add the extended tiles
        if (false && extended) {
            self._addSymbolCollection(EXTENDED_SYMBOLS, 1);
        }

        console.log('Initialized deck with ' + self.tile_list.length + ' tiles.');
    }

    this._addSymbolCollection = function(symbol_collection, copies=1) {
        // Go through symbol_sets
        Object.keys(symbol_collection).forEach((key) => {
            let symbol_set = symbol_collection[key];
            console.log(key + ': ' + symbol_set[2]);

            // Add symbols from the symbol_set to tiles
            for (let t = 0; t < symbol_set[2]; t++) {
                for (let c = 0; c < copies; c++) {
                    self.tile_list.push(new Tile(symbol_set[0] + t, symbol_set[1]));
                }
            }
            if(config.age > 4){
                for(let i = 0 ; i < config.age - 4 && i < 10; i++){
                    var ind = Math.floor(Math.random() * symbol_set[2]);
                    self.tile_list.push (new Tile(symbol_set[0] + ind, symbol_set[1]));
                    self.tile_list.push (new Tile(symbol_set[0] + ind, symbol_set[1]));
                }
            }
        });
        console.log("Tile list:");
		console.log(self.tile_list);
    }

    this.shuffle = function() {
        // Define temporary holding area for tiles
        const temp_holder = [];

        // Take tiles out of the deck in random order
        while(self.tile_list.length > 0) {
            let r_i = Math.floor(Math.random() * self.tile_list.length);
            temp_holder.push(self.tile_list[r_i]);
            self.tile_list.splice(r_i, 1);
        }

        // Put randomized tiles back to deck
        temp_holder.forEach((value) => { self.tile_list.push(value); });

        console.log('Deck shuffled, cards in deck: ' + self.tile_list.length);
    }

    this.popTile = function() {
        // Make sure there are enough tiles to pop
        if (self.tile_list.length > 0) {
            let top_tile = self.tile_list.pop();
            return top_tile;
        }
        else {
            console.error('This tile deck is empty, cannot pop.');
            return new Tile(2, 6);
        }
    }
}

function Tile(x, y, available=false) {
    const self = this;

    this.available = available;
    this.x = x;
    this.y = y;

    this.matches = function(tile) {
        if (self.y === EXTENDED_SYMBOLS.SEASONS[1]) {
            //console.log('Special case, comparing against seasons tile');
            return self.y === tile.y;
        }
        else if (self.y === EXTENDED_SYMBOLS.FLOWERS[1]) {
            //console.log('Special case, comparing against flowers tile');
            return self.y === tile.y;
        }
        else {
            return self.x === tile.x && self.y === tile.y;
        }
    }

    this.draw = function(drawer, pos) {
        drawer.drawTile(self, drawer.getBoundingBoxBasic(pos));
    }
}