package com.ibrahimbdj.todolist;

import java.util.ArrayList;
import java.util.List;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@SpringBootApplication
public class TodolistApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(TodolistApplication.class, args);
	}
	
	@RestController
	public class Todolistservice {
		
		private List<Todolist> tdls;
		
		public Todolistservice() {
			this.tdls = new ArrayList<Todolist>();
		}
		
		
		@GetMapping("/")
		public String home() {
			return "Mode d'utilisation:<br><br>"
					+ "GET /todolist: liste des todolist<br>"
					+ "GET /todolist/{id_todolist}: affiche une todolist<br>"
					+ "POST /todolist: crée une liste avec le nom du parametre<br>"
					+ "PUT /todolist/{id_todolist}: modifie une todolist<br>"
					+ "DELETE /todolist/{id_todolist}: supprime une todolist<br><br>"
					+ "Une todolist créée a un id donné automatiquement afin de simplifier les "
					+ "chose attention de bien mettre l'id lors du test il seras visible sur "
					+ "la liste des todolist<br>"
					+ "pour voir les mise à jour faites avec post/put/delete n'oubliez "
					+ "pas de refaire un appel GET<br>";
		}
		
		@GetMapping("/todolist")
		private String tdls(){
			String s = "";
			for (int i = 0; i < tdls.size(); i++) {
				s = s + tdls.get(i).getTitle()+ " , id: " + tdls.get(i).getId() + "<br>";
			}
			
			return s;
		}
		
		@GetMapping("/todolist/{id}")
		private String tdl(@PathVariable("id") int id){
			for (int i = 0; i < tdls.size(); i++) {
				if(tdls.get(i).getId() == id) {
					Todolist tdl = tdls.get(i);
					String s = "<strong>" + tdl.getTitle() + ":</strong><br><br>";
					
					for (int j = 0; j < tdl.getTaches().size(); j++) {
						s = s + tdl.getTaches().get(j) + ";<br>";
					}
					return s;
				}
			}
			return "cette todolist n'existe pas";
		}
		
		@PostMapping("/todolist")
		@ResponseStatus(HttpStatus.CREATED)
		private void create_tdl(@RequestParam(value="title", required=true) String title) {
			tdls.add(new Todolist(title));
		}
		
		@PutMapping("/todolist/{id}")
		@ResponseStatus(HttpStatus.OK)
		private void update_tdl(@PathVariable("id") int id, @RequestBody Todolist tdl) {
			boolean found = false;
			for (int i = 0; i < tdls.size(); i++) {
						if(tdls.get(i).getId() == id) {
							tdls.set(i, tdl);
							tdl.setId(id);
							found = true;
						}
			}
			if(!found) {
				throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Todolist introuvable");
			}
		}
		
		@DeleteMapping("/todolist/{id}")
		@ResponseStatus(HttpStatus.OK)
		private void delete_tdl(@PathVariable("id") int id) {
			for (int i = 0; i < tdls.size(); i++) {
				if(tdls.get(i).getId() == id) tdls.remove(i);
			}
		}
	}

}
